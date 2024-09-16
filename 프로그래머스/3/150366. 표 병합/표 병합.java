import java.util.*;
class Solution {
    static int[] parents;
    static String[] values;
    static List<String> results;
    static final int N = 2500;
    
    public String[] solution(String[] commands) {
        parents = new int[N];
        values = new String[N];
        results = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
        
        for (String input : commands) {
            String[] arr = input.split(" ");
            String command = arr[0];
            
            if ("UPDATE".equals(command)) {
                if (arr.length == 3) {
                    updateValue(arr[1], arr[2]);
                    continue;
                }
                
                int x = Integer.parseInt(arr[1]) - 1;
                int y = Integer.parseInt(arr[2]) - 1;
                updatePosition(x, y, arr[3]);
            } else if ("MERGE".equals(command)) {
                int x1 = Integer.parseInt(arr[1]) - 1;
                int y1 = Integer.parseInt(arr[2]) - 1;
                int x2 = Integer.parseInt(arr[3]) - 1;
                int y2 = Integer.parseInt(arr[4]) - 1;
                
                merge(x1, y1, x2, y2);                
            } else if ("UNMERGE".equals(command)) {
                int x = Integer.parseInt(arr[1]) - 1;
                int y = Integer.parseInt(arr[2]) - 1;
                unmerge(x, y);
            } else {
                int x = Integer.parseInt(arr[1]) - 1;
                int y = Integer.parseInt(arr[2]) - 1;
                print(x, y);
            }
        }
        
        return results.toArray(new String[results.size()]);
    }
    
    private void updatePosition(int x, int y, String value) {
        values[find(50 * x + y)] = value;
    }
    
    private void updateValue(String oldValue, String newValue) {
        for (int i = 0; i < N; i++) {
            int idx = find(i);
            if (oldValue.equals(values[idx])) {
                values[idx] = newValue;
            }
        }
    }
    
    private void merge(int x1, int y1, int x2, int y2) {
        int index1 = find(50 * x1 + y1);
        int index2 = find(50 * x2 + y2);
        String value1 = values[index1];
        String value2 = values[index2];
        
        // x1, y1이 아닌 x2, y2에만 값이 있는 경우 
        // 현재 union은 기본적으로 x1, y1 대표를 x2, y2의 대표로 변경하며 x1, y1기준으로 병합
        // 따라서 x1, y1과 x2, y2의 위치를 변경해야 한다. 
        if (value1 == null && value2 != null) {
            int tmp = index1;
            index1 = index2;
            index2 = tmp;
        }
        
        union(index1, index2);
    }
    
    private void unmerge(int x, int y) {
        int index = find(50 * x + y);
        String value = values[index];
        
        for (int i = 0; i < N; i++) {
            find(i);
        }
        
        for (int i = 0; i < N; i++) {
            if (parents[i] == index) {
                parents[i] = i;
                
                if (i == 50 * x + y) {
                    values[i] = value;
                    continue;
                } 
                
                values[i] = null;
            }
        }
    }
    
    private void print(int x, int y) {
        String value = values[find(50 * x + y)];
        results.add(value == null ? "EMPTY" : value);
    }
    
    private int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        
        return parents[x] = find(parents[x]);
    }
    
    private void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        // 같은 그룹이라 병합 안해도 된다.
        if (a == b) {
            return;
        }
        
        // 만약 x1, y1과 x2, y2에 둘다 값이 있을 경우 x1, y1을 따르기 때문에 
        // x1, y1에 해당하는 먼저 나온 a를 기준으로 병합한다. 
        // values관리는 각 그룹의 대표로 관리하고 찾을 때는 항상 find()를 이용하여 대표를 찾아 처리
        values[b] = null;
        parents[b] = a;
    }
}