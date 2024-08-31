import java.util.*;
class Solution {
    int N, C;
    Set<Integer> original, additional;
    public int solution(int coin, int[] cards) {
        int answer = 0;
        N = cards.length;
        C = coin;
        original = new HashSet();
        additional = new HashSet();
        
        int idx = N / 3;
        for(int i = 0 ; i < idx; ++i)
            original.add(cards[i]);
        
        int target = N + 1;   
        while(true){
            answer++;
            if(idx >= N){
                break;
            }
            additional.add(cards[idx++]);
            additional.add(cards[idx++]);
            
            // 완성되지 않았다.
            if(!isValid(target)) {
                break;
            }
        }
        return answer;
    }
    
    private boolean isValid(int target) {
        // Step1. 최초 카드에서 해결할 수 있는지 확인.
        for (int o : original) {
            if (original.contains(target - o)) {
                original.remove(o);
                original.remove(target - o);
                return true;
            }
        }
        
        // Step2. 최초 카드에서 해결이 안되었다면
        // 최초 카드와 라운드 추가 카드 1장을 이용해서 해결 할 수 있는지 확인.
        // 최소 1개 이상의 코인이 있어야 추가 카드를 받아서 사용할 수 있다.
        if (C > 0) {
            for (int o : original) {
                if (additional.contains(target - o)) {
                    original.remove(o);
                    additional.remove(target - o);
                    C--;
                    return true;
                }
            }
        }
        
        // Step3. 그래도 해결이 안되었다면, 추가 카드들 간에 해결이 가능한 지 확인.
        // 최소 2개 이상의 코인이 있어야 추가 카드를 중에서 해결이 가능하다.
        if (C >= 2) {
            for (int a : additional) {
                if (additional.contains(target - a)) {
                    additional.remove(a);
                    additional.remove(target - a);
                    C-=2;
                    return true;
                }
            }
        }
        
        return false;
    }
 
}