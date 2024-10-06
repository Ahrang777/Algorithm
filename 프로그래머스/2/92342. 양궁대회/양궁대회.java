import java.util.*;
class Solution {
    static int N, max;
    static int[] answer;
    static boolean flag;
    static final int LEN = 11;
    public int[] solution(int n, int[] info) {
        int[] lion = new int[LEN];
        answer = new int[LEN];
        N = n;
        
        // 어파치가 맞춘 점수 중 가장 큰 점수보다 더 큰 점수들 중 못 맞춘 점수들은 다 획득 >> 1개씩 소모
        // 점수차 동일할 경우 가장 낮은 점수 더 많은 걸로 선택 >> 뒤에서부터 채우기
        dfs(0, 0, info, lion);
        
        if (!flag) {
            answer = new int[]{-1};
        }
        return answer;
    }
    
    // 현재까지 사용한 화살 수, 현재 확인해야 할 점수판 index, 어파치 점수판 현황, 라이언 점수판 현황
    private void dfs(int cnt, int now, int[] apa, int[] lion) {
        if (cnt == N) {
            int apaSum = 0;
            int lionSum = 0;
            for (int i = 0; i < LEN; i++) {
                if (lion[i] > 0 || apa[i] > 0) {
                    if (lion[i] > apa[i]) {
                       lionSum += (LEN - i - 1);
                    } else {
                        apaSum += (LEN - i - 1);
                    }
                }
            
            }
            
            if (lionSum > apaSum) {
                int diff = lionSum - apaSum;
                
                if (!flag) {
                    max = diff;
                    flag = true;
                    answer = lion.clone();
                } else {
                    if (max > diff) return;
                    
                    if (max == diff) {
                        for (int i = LEN - 1; i >= 0; i--) {
                            if (answer[i] > lion[i]) {
                                return;
                            } else if (lion[i] > answer[i]) {
                                break;
                            }
                        }
                    } 
                    
                    for (int i = 0; i < LEN; i++) {
                        answer[i] = lion[i];
                    }
                    max = diff;
                }
                
                
            }
            
            return;
        }
        
        if (now == LEN) {
            return;
        }
        
        for (int i = 0; i <= N - cnt; i++) {
            lion[now] = i;
            dfs(cnt + i, now + 1, apa, lion);
            lion[now] = 0;
        }
    }
}