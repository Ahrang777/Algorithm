class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 100000;
        int N = diffs.length;
        int s = 1, e = 100000;
        
        while (s <= e) {
            // 숙련도
            int mid = (s + e) / 2;
            
            int diff = diffs[0];    // 현재 퍼즐의 난이도
            int curTime = times[0]; // 현재 퍼즐의 소요 시간
            int prevTime = 0;   // 이전 퍼즐의 소요 시간
            long total = diff <= mid ? curTime : (diff - mid) * (curTime + prevTime) + curTime;
            
            for (int i = 1; i < N; i++) {
                diff = diffs[i];
                curTime = times[i];
                prevTime = times[i - 1];
                
                if (diff <= mid) {
                    total += curTime;
                } else {
                    total += ((diff - mid) * (curTime + prevTime) + curTime);
                }
            }
            
            if (total <= limit) {
                answer = Math.min(answer, mid);
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        
        return answer;
    }
}