import java.util.*;
class Solution {
    static int N, M;
    static List<int[]> discountRates;
    public int[] solution(int[][] users, int[] emoticons) {
        N = users.length;
        M = emoticons.length;
        
        discountRates = new ArrayList<>();
        
        dfs(0, new int[M]);
        
        int maxMembers = 0;
        int maxTotal = 0;
        
        // 할인율 후보마다 계산
        for (int[] discount : discountRates) {
            int members = 0;
            int total = 0;
            
            // 각 사용자 기준마다 계산
            for (int[] u : users) {
                // 현재 사용자의 구매한 이모티콘 금액
                int sum = 0;
                for (int i = 0; i < M; i++) {
                    int d = discount[i];
                    if (d < u[0]) {
                        continue;
                    }
                    
                    sum += (emoticons[i] * (100 - d) / 100);
                    if (sum >= u[1]) {
                        sum = 0;
                        members++;
                        break;
                    }
                }
                
                total += sum;
                
            }
            
            if (maxMembers < members) {
                maxMembers = members;
                maxTotal = total;
            } else if (maxMembers == members && maxTotal < total) {
                maxTotal = total;
            }
        }
            
        return new int[]{maxMembers, maxTotal};
    }
    
    private void dfs(int cnt, int[] discountRate) {
        if (cnt == M) {
            discountRates.add(discountRate.clone());
            return;
        }
        
        for (int i = 10; i <= 40; i+=10) {
            discountRate[cnt] = i;
            dfs(cnt + 1, discountRate);
        }
    }
}