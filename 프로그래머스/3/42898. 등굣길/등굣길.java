class Solution {
    static final int DIV = 1000000007;
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n + 1][m + 1];

        dp[1][1] = 1;
        
        // 주의 >> x, y가 아니라 y, x로 puddle에 저장되어 있다.
        for (int[] puddle : puddles) {
            dp[puddle[1]][puddle[0]] = -1;
        }
        
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= m; y++) {
                if (dp[x][y] != -1) {
                    dp[x][y] += (Math.max(dp[x - 1][y], 0) + Math.max(dp[x][y - 1], 0));
                    dp[x][y] %= DIV;
                }
            }
        }
        
        return dp[n][m];
    }
}