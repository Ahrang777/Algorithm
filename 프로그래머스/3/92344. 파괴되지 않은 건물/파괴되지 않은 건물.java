class Solution {
    // 행, 열, 가장 왼쪽 위 지점, 가장 오른쪽 아래 지점
    static int N, M;
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        N = board.length;
        M = board[0].length;
        
        int[][] imos = new int[N][M];
        
        imos(imos, skill);
        
        answer = count(imos, board);
        
        return answer;
    }
    
    private int count(int[][] imos, int[][] board) {
        int answer = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (imos[i][j] + board[i][j] > 0) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    private void imos (int[][] imos, int[][] skill) {
        // imos기법으로 각 영역별 -, +로 표시만 해두고 
        // 마지막에 한번에 정리하여 나온 크기와 원래 board 내구도와 비교하여 0 이하인 값들의 개수 반환
        
        // [type, r1, c1, r2, c2, degree]
        for (int[] s : skill) {
            int type = s[0];
            int x1 = s[1];
            int y1 = s[2];
            int x2 = s[3];
            int y2 = s[4];
            int degree = s[5];
            
            // 간단하게 회복일 경우 먼저 생각하면 아래와 같다. 공격은 +, -만 반대로 하면 된다.
            // x1, y1에 +degree 기록
            // x1, y1 + 1에 -degree 기록
            // x2 + 1, y1에 -degree 기록
            // x2 + 1, y2 + 1에 +degree 기록
            if (type == 1) {    // 공격
                degree = -degree;
            }             
            
            imos[x1][y1] += degree;
                
            if (isRange(x1, y2 + 1)) {
                imos[x1][y2 + 1] -= degree;
            }
                
            if (isRange(x2 + 1, y1)) {
                imos[x2 + 1][y1] -= degree;
            }
                
            if (isRange(x2 + 1, y2 + 1)) {
                imos[x2 + 1][y2 + 1] += degree;
            }
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < M; j++) {
                imos[i][j] += imos[i][j - 1];
            }
        }
        
        for (int j = 0; j < M; j++) {
            for (int i = 1; i < N; i++) {
                imos[i][j] += imos[i - 1][j];
            }
        }
    }
    
    private boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}