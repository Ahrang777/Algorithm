import java.util.*;

class Solution {
    
    static class Job {
        int index, p;
        
        public Job(int index, int p) {
            this.index = index;
            this.p = p;
        }
    }
    
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int N = priorities.length;
        
        Queue<Job> q = new ArrayDeque<>();
        
        for (int i = 0; i < N; i++) {
            q.offer(new Job(i, priorities[i]));
        }
        
        Arrays.sort(priorities);
        
        int len = priorities.length - 1;
        int index = 0;
        
        while (!q.isEmpty()) {
            Job now = q.poll();
            int p = priorities[len - index];
            
            if (now.p == p) {
                index++;
                answer++;
                
                if (now.index == location) break;
            } else {
                q.offer(now);
            }
        }
        
        return answer;
    }
}