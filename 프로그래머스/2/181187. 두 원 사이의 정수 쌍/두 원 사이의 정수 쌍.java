class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
                
        double r1Pow = Math.pow(r1, 2);
        double r2Pow = Math.pow(r2, 2);
        
        for (int x = 1; x <= r2; x++) {       
            double xPow = Math.pow(x, 2);
            
            double y1 = Math.sqrt(r1Pow - xPow);
            if (y1 > Math.floor(y1)) {
                y1 = Math.ceil(y1);
            }
            
            int y2 = (int) Math.sqrt(r2Pow - xPow);
            answer += (y2 - (int) y1 + 1);
        }
        
        return answer * 4;
    }
}