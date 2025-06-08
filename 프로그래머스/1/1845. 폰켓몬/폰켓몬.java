import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int N = nums.length;
    
        // 일단 번호 중복없이 Set에 넣기 
        // 그 크기가 N / 2이면 N / 2가 정답, 아니면 Set의 크기가 정답
        Set<Integer> set = new HashSet<>();
        
        for (int num : nums) {
            set.add(num);
        }
        
        answer = set.size() >= N / 2 ? N / 2 : set.size();
        
        return answer;
    }
}