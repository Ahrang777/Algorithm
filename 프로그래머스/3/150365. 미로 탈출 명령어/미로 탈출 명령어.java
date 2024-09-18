import java.util.*;

class Solution {
    static int N, M, K, X, Y, R, C;
    static boolean flag;
    // 사전상 가장 앞에 오는 순으로 방향 지정해서 순서대로 확인할 경우 사전 순이 되도록 설정
    static char[] dir = {'d', 'l', 'r', 'u'};
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static String answer;
    
    static final String IMPOSSIBLE = "impossible";
    
    // 완전탐색을 해야하지만 시간, 공간복잡도를 생각해야 하는 경우 
    // dfs + 가지치기, 바로 가능여부 판단 가능한지를 고려하자
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;  M = m;  K = k;
        X = x - 1;  Y = y - 1;  R = r - 1;  C = c - 1;
        
        int distance = getDistance(x, y, r, c); // 최소거리
        
        // 1. 최소 이동거리가 이동가능한 횟수보다 크면 불가능
        // 2. 이동가능한 횟수가 최소 이동거리보다 클 때 
        // 같은 자리에서 왕복(ex. LR)하는 경우 2씩 횟수가 늘어난다.
        // 따라서 이동가능한 횟수 - 최소 이동거리의 결과가 홀수면 불가능하다.
        if (distance > K || (K - distance) % 2 == 1) {
            return IMPOSSIBLE;
        }
        
        dfs(X, Y, new ArrayList<>());
        return answer;
    }
    
    private int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    private void dfs(int x, int y, List<Integer> history) {
        // 이미 정답을 찾은 경우
        if (flag)   return;
        
        // K만큼 이동해서 도착지점에 도착한 경우
        if (history.size() == K && x == R && y == C) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < K; i++) {
                sb.append(dir[history.get(i)]);
            }
            
            answer = sb.toString();
            flag = true;
            return;
        } 
        
        // 현재위치 ~ 도착지점 최소거리 > 남은 이동횟수
        if (getDistance(x, y, R, C) > K - history.size())  return;
        
        for (int i = 0; i < 4 && !flag; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (!isRange(nx ,ny))   continue;
            
            history.add(i);
            dfs(nx, ny, history);
            int size = history.size();
            history.remove(size - 1);
        }
    }
    
    private boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}