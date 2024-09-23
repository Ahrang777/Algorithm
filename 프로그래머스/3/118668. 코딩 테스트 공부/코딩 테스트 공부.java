import java.util.*;
class Solution {
    static final int INF = (int) 1e9;
    
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;
        
        // [alp_req, cop_req, alp_rwd, cop_rwd, cost]
        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        
        maxAlp = Math.max(maxAlp, alp);
        maxCop = Math.max(maxCop, cop);
        
        if (alp >= maxAlp && cop >= maxCop) {
            return 0;
        }
        
        // 현재 알고력, 코딩력
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        
        for (int i = 0; i <= maxAlp; i++) {
            Arrays.fill(dp[i], INF);
        }
        
        dp[alp][cop] = 0;
        
        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                if (i < maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i][j] + 1, dp[i + 1][j]);
                }
                
                if (j < maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j] + 1, dp[i][j + 1]);
                }
                
                // problem : [alp_req, cop_req, alp_rwd, cop_rwd, cost]
                for (int[] problem : problems) {
                    if (i >= problem[0] && j >= problem[1]) {
                        int x = i + problem[2] > maxAlp ? maxAlp : i + problem[2];
                        int y = j + problem[3] > maxCop ? maxCop : j + problem[3];
                    
                        dp[x][y] = Math.min(dp[x][y], dp[i][j] + problem[4]);
                    }
                }
            }
        }
        
        return dp[maxAlp][maxCop];
    }
}