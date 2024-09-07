import java.util.*;
class Solution {
    static class Box {
        int len, cnt;
        
        public Box (int len, int cnt) {
            this.len = len;
            this.cnt = cnt;
        }
    }
    static int N;
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        N = n;
        
        // 거리가 먼 순서대로 들어있음
        Deque<Box> dq = new ArrayDeque<>(); // 배달
        Deque<Box> pq = new ArrayDeque<>();; // 수거
        
        for (int i = N - 1; i >= 0; i--) {
            if (deliveries[i] > 0) {
                dq.offerLast(new Box(i + 1, deliveries[i]));
            }
            
            if (pickups[i] > 0) {
                pq.offerLast(new Box(i + 1, pickups[i]));
            }
        }
        
        while (!dq.isEmpty() || !pq.isEmpty()) {
            int dLen = dq.isEmpty() ? 0 : dq.peek().len;
            int pLen = pq.isEmpty() ? 0 : pq.peek().len;
            
            // 트럭에 실어 출발할 상자의 개수 
            // 상자 배달
            int dCnt = 0;
            while (!dq.isEmpty() && dCnt < cap) {
                Box d = dq.pollFirst();
                
                int needCnt = cap - dCnt;
                if (d.cnt > needCnt) {
                    d.cnt -= needCnt;
                    dq.offerFirst(d);
                    dCnt = cap;
                } else {
                    dCnt += d.cnt;
                }
            }
            
            // 상자 수거 
            int pCnt = 0;
            while (!pq.isEmpty() && pCnt < cap) {
                Box p = pq.pollFirst();
                
                int needCnt = cap - pCnt;
                if (p.cnt > needCnt) {
                    p.cnt -= needCnt;
                    pq.offerFirst(p);
                    pCnt = cap;
                } else {
                    pCnt += p.cnt;
                }
            }
            
            answer += 2 * Math.max(dLen, pLen);
        }
        
        return answer;
    }
}