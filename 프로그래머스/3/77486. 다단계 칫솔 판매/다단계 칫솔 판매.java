import java.util.*;

class Solution {
    // 이름, 소유 금액
    Map<String, Integer> map = new HashMap<>();
    
    // 대상 이름, 추천인 이름
    Map<String, String> parents = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        int[] answer = new int[enroll.length];
        
        map.put("-", 0);
        for (int i = 0; i < enroll.length; i++) {
            String c = enroll[i];
            String p = referral[i];
            
            map.put(c, 0);
            parents.put(c, p);
        }
        
        for (int i = 0; i < seller.length; i++) {
            
            String s = seller[i];
            int price = 100 * amount[i];
            
            // 현재 대상이 - 가 될때까지 반복
            while (!s.equals("-")) {
                // 추천인
                String p = parents.get(s);
                int parentPrice = (int) (price * 0.1);
                int myPrice = price- parentPrice;
                
                if (parentPrice < 1) {
                    myPrice = price;
                    parentPrice = 0;
                }
                
                map.put(s, map.get(s) + myPrice);
                
                // 더이상 진행할 필요X
                if (parentPrice == 0) {
                    break;
                }
                
                price = parentPrice;
                s = parents.get(s);
            }
        }
        
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = map.get(enroll[i]);
        }
        
        return answer;
    }
}