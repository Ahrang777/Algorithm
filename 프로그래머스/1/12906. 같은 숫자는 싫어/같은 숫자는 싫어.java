import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer;
        Stack<Integer> s = new Stack<>();
        
        for (int n : arr) {
            if (s.isEmpty() || s.peek() != n) {
                s.push(n);
            }
            
        }
        
        answer = new int[s.size()];
        
        for (int i = 0; i < s.size(); i++) {
            answer[i] = s.get(i);
        }
        
        
        return answer;
    }
}