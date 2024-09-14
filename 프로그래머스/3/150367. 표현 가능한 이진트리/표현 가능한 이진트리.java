import java.util.*;
class Solution {
    static int result;
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);
            String fullBinary = getFullBinary(binary);
            int len = fullBinary.length();
            
            answer[i] = isValid(fullBinary, 0, len - 1) ? 1 : 0;      
        }
        
        return answer;
    }
    
    private boolean isValid(String binary, int start, int end) {
        if (start >= end) return true;
        
        int mid = (start + end) / 2;
        
        if (binary.charAt(mid) == '0') {
            int leftCnt = 0;
            int rightCnt = 0;
            
            for (int i = start; i <= mid - 1; i++) {
                if (binary.charAt(i) == '1') leftCnt++;
            }
            
            for (int i = mid + 1; i <= end; i++) {
                if (binary.charAt(i) == '1') rightCnt++;
            }
            
            return leftCnt == 0 && rightCnt == 0;
        }
        
        return isValid(binary, start, mid - 1) && isValid(binary, mid + 1, end);
    }
    
    private String getFullBinary(String binary) {
        int len = binary.length();
        StringBuilder sb = new StringBuilder();
            
        // 포화 이진트리로 만들기 >> 길이가 2^N - 1 가 되야한다. 
        // 높이 N인 포화 이진트리의 노드 개수는 2^N - 1
        // 그만큼 맨 앞에 0을 붙여주자 
        int cnt = 0;
        while (Math.pow(2, cnt) - 1 < len) {
            cnt++;
        }
        
        for (int i = 0; i < Math.pow(2, cnt) - 1 - len; i++) {
            sb.append(0);
        }
        sb.append(binary);
        return sb.toString();
    }
}