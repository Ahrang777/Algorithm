class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int indexD = n - 1;
        int indexP = n - 1;
        
        while (indexD >= 0 || indexP >= 0) {
            
            // 제일 먼 배송지점 구하기 
            while (indexD >= 0 && deliveries[indexD] == 0) indexD--;
            
            // 제일 먼 수거지점 구하기
            while (indexP >= 0 && pickups[indexP] == 0) indexP--;
            
            // 배송할 박스의 개수, 수거할 박스의 개수 
            int sum = 0;
            
            // 배송 지점, 수거 지점 중 더 먼곳으로 이동 
            // 가는 길에 배송하거나 오는 길에 수거
            answer += (Math.max(indexD, indexP) + 1) * 2;
            
            // 배송처리 
            while (indexD >= 0 && sum < cap) {
                sum += deliveries[indexD];
                deliveries[indexD--] = 0;
            }
                        
            if (sum > cap) {
                deliveries[++indexD] = sum - cap;
            }
            
            sum = 0;

            // 수거처리
            while (indexP >= 0 && sum < cap) {
                sum += pickups[indexP];
                pickups[indexP--] = 0;
            }
            
            if (sum > cap) {
                pickups[++indexP] = sum - cap;
            }
        }
        
        return answer;
    }
}