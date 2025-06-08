import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        // 단순비교: 10만 * 10만 > 1억 (시간초과)
        // 대상을 찾는 과정에서 시간단축 필요
        // Map이용시 찾는 과정을 O(1)로 단축가능
        // 문제는 동명이인, 참여자의 이름마다 + 1씩 하고 완주자들을 하나씩 확인하며 값 - 1하자. 
        // 최종으로 값이 0이 아닌 참여자가 완주 못한 선수
    
        Map<String, Integer> map = new HashMap<>();
        
        for (String p : participant) {
            map.put(p, map.getOrDefault(p, 0) + 1);
        }
        
        for (String c : completion) {
            map.put(c, map.get(c) - 1);
        }
        
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            if (e.getValue() > 0) {
                answer = e.getKey();
                break;
            }
        }        
        
        return answer;
    }
}