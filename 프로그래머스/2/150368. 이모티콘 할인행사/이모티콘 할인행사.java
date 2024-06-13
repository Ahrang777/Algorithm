import java.util.*;

class Solution {
    static int N;
    static int serviceResult, totalResult;
    static int[] output;
    static boolean[] visited;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        N = emoticons.length;
        
        output = new int[N];
        visited = new boolean[N];
        
        permutation(0, users, emoticons);
        
        answer[0] = serviceResult;
        answer[1] = totalResult;
        return answer;
    }
    
    // 중복 순열
    private void permutation(int cnt, int[][] users, int[] emoticons) {
        if (cnt == N) {
            int total = 0;  // 이모티콘 판매액
            int service = 0;    // 이모티콘 플러스 서비스 가입자 수
            
            // 얻어낸 output을 이용하여 사람마다 판매, 구매 구하기
            for (int[] user : users) {
                int percent = user[0];
                int price = user[1];
                int totalPrice = 0;
                
                for (int i = 0; i < N; i++) {
                    int out = output[i];
                    int emoticon = emoticons[i];
                    
                    // 이모티콘 구매
                    if (out >= percent) {
                        totalPrice += (emoticon / 100) * (100 - out);
                    }
                    
                    // 이모티콘 플러스 서비스 가입
                    if (totalPrice >= price) {
                        totalPrice = 0;
                        service++;
                        break;
                    }
                }
                
                total += totalPrice;
            }
            
            if (service > serviceResult) {
                serviceResult = service;
                totalResult = total;
            } else if (service == serviceResult && total > totalResult) {
                totalResult = total;
            }
            
            return;
        }
        
        for (int i = 10; i <= 40; i+=10) {
            output[cnt] = i;
            permutation(cnt + 1, users, emoticons);
        }
    }
}