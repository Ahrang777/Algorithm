import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (o1, o2) -> Integer.compare(o1[1], o2[1]));
        
        int prev = 0;
        
        for (int[] target : targets) {
            if (target[0] >= prev) {
                prev = target[1];
                answer++;
            }
        }
        
        return answer;
    }
}