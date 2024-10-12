class Solution {
    public int solution(String arr[]) {
        int len = arr.length;
        int max, min, sum;
        max = min = sum = 0;
        
        for (int i = len - 1; i > 0; i-=2) {
            int num = Integer.parseInt(arr[i]);
            String op = arr[i - 1];
            
            if (op.equals("+")) {
                sum += num;
            } else {
                // 최대값 후보
                int r1 = -(num + sum + min);
                int r2 = -num + sum + max;
                
                // 최소값 후보
                int r3 = -(num + sum + max);
                int r4 = -(num + sum) + min;
                
                max = Math.max(r1, r2);
                min = Math.min(r3, r4);
                sum = 0;
            }
        } 
        
        return Integer.parseInt(arr[0]) + sum + max;
    }
}