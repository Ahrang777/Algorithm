import java.util.*;

class Solution {
    static class Position {
        int x, y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static String[][] table;
    static int[][] group;
    static List<String> results;
    static Map<Integer, List<Position>> map;
    static int index;
    static final int N = 50;
    public String[] solution(String[] commands) {        
        table = new String[N][N];
        group = new int[N][N];
        results = new ArrayList<>();
        map = new HashMap<>();
        
        for (String command : commands) {
            String[] arr = command.split(" ");
            String c = arr[0];
            if (c.equals("UPDATE")) {
                if (arr.length == 4) {  //UPDATE r c value
                    int x = Integer.parseInt(arr[1]) - 1;
                    int y = Integer.parseInt(arr[2]) - 1;
                    
                    updatePosition(x, y, arr[3]);
                    continue;
                } 
            
                // UPDATE value1 value2
                updateValue(arr[1], arr[2]);
            } else if (c.equals("MERGE")) {
                int x1 = Integer.parseInt(arr[1]) - 1;
                int y1 = Integer.parseInt(arr[2]) - 1;
                int x2 = Integer.parseInt(arr[3]) - 1;
                int y2 = Integer.parseInt(arr[4]) - 1;
                
                merge(x1, y1, x2, y2);
            } else if (c.equals("UNMERGE")) {
                unmerge(Integer.parseInt(arr[1]) - 1, Integer.parseInt(arr[2]) - 1);
            } else {
                print(Integer.parseInt(arr[1]) - 1, Integer.parseInt(arr[2]) - 1);
            }
        }
        
        
        String[] answer = new String[results.size()];
        for (int i = 0; i < results.size(); i++) {
            answer[i] = results.get(i);
        }
        
        return answer;
    }
    
    private void updatePosition(int x, int y, String value) {
        // group 까지 변화
        if (group[x][y] > 0) {
            List<Position> list = map.getOrDefault(group[x][y], new ArrayList<>());
            for (Position p : list) {
                table[p.x][p.y] = value;
            }
            return;
        }
        table[x][y] = value;
    }
    
    private void updateValue(String oldValue, String newValue) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (oldValue.equals(table[x][y])) {
                    table[x][y] = newValue;
                }
            }
        }
    }
    
    private void merge(int x1, int y1, int x2, int y2) {
        // x1, y1  x2, y2 병합 (이미 그룹에 속해있다면 해당 그룹으로 편입, 이미 같은 그룹이면 무시)
        if (group[x1][y1] > 0 && group[x1][y1] == group[x2][y2])   return;
        
        // 둘다 그룹
        if (group[x1][y1] > 0 && group[x2][y2] > 0) {
            List<Position> list1 = map.getOrDefault(group[x1][y1], new ArrayList<>());
            List<Position> list2 = map.getOrDefault(group[x2][y2], new ArrayList<>());
            
            // x2, y2의 그룹을 없애고 x1, y1 그룹으로 병합
            list1.addAll(list2);
            map.remove(group[x2][y2]);
            for (Position p : list2) {
                group[p.x][p.y] = group[x1][y1];
            }
        } else if (group[x1][y1] > 0) { // 왼쪽만 그룹
            // x2, y2를 그룹으로 추가
            List<Position> list = map.getOrDefault(group[x1][y1], new ArrayList<>());
            list.add(new Position(x2, y2));
            group[x2][y2] = group[x1][y1];
        } else if (group[x2][y2] > 0) { // 오른쪽만 그룹
            // x1, y1을 그룹으로 추가 
            List<Position> list = map.getOrDefault(group[x2][y2], new ArrayList<>());
            list.add(new Position(x1, y1));
            group[x1][y1] = group[x2][y2];
        } else { // 둘다 그룹 아님
            group[x1][y1] = group[x2][y2] = ++index;
            List<Position> list = new ArrayList<>();
            list.add(new Position(x1, y1));
            list.add(new Position(x2, y2));
            map.put(index, list);
        }
        
        // table 값 반영
        String value1 = table[x1][y1];
        String value2 = table[x2][y2];
        String value = value1 != null ? value1 : value2;
        List<Position> list = map.getOrDefault(group[x1][y1], new ArrayList<>());
        
        if (value != null) {
            // 현재 그룹의 모든 값을 value로 
            for (Position p : list) {
                table[p.x][p.y] = value;
            }
        } 
    }
    
    private void unmerge(int x, int y) {
        // x, y가 속한 그룹의 병합 해제 
        // 값 없으면 그냥 분리
        // 값 있다면 x, y에 해당 값을 넣고 나머지 셀은 값 없는 초기 상태로 만들기 
        String value = table[x][y];
        
        List<Position> list = map.getOrDefault(group[x][y], new ArrayList<>());
        for (Position p : list) {
            table[p.x][p.y] = null;
            group[p.x][p.y] = 0;
        }
        map.remove(group[x][y]);
        
        if (value != null) {
            table[x][y] = value;
        }
    }
    
    private void print(int x, int y) {
        results.add(table[x][y] == null ? "EMPTY" : table[x][y]);
    }
}