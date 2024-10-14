class Solution {
    public int solution(int[] money) {
        int N = money.length;
        // N - 2, N - 1, 0, 1 집이 연결되어 있다. 
        // 단순 i - 1, i - 2 비교로는 마지막에 문제 >> 0번째 집 선택한 경우, 선택 안 한 경우로 구분?
        
        // 0: 0번 포함, 1: 0번째 건너뛰고 1번 부터 포함
        int[][] dp = new int[2][N];
        
        dp[0][0] = dp[0][1] = money[0];
        dp[1][1] = money[1];
        
        for (int i = 2; i < N - 1; i++) {
            dp[0][i] = Math.max(dp[0][i - 1], dp[0][i - 2] + money[i]);
            dp[1][i] = Math.max(dp[1][i - 1], dp[1][i - 2] + money[i]);
        }
        
        dp[0][N - 1] = dp[0][N - 2];
        dp[1][N - 1] = Math.max(dp[1][N - 2], dp[1][N - 3] + money[N - 1]);
        
        return Math.max(dp[0][N - 1], dp[1][N - 1]);
    }
}