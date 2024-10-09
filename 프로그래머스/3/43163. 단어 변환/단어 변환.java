import java.util.*;
class Solution {
    static class Word {
        int cnt;
        String content;
        
        public Word(int cnt, String content) {
            this.cnt = cnt;
            this.content = content;
        }
    }
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int len = words.length;
        
        Queue<Word> q = new ArrayDeque<>();
        boolean[] visited = new boolean[len];
        
        q.offer(new Word(0, begin));
        
        while (!q.isEmpty()) {
            Word now = q.poll();
            String content = now.content;
            
            if (target.equals(content)) {
                answer = now.cnt;
                break;
            }
            
            for (int i = 0; i < len; i++) {
                // 변환 가능한 경우
                if (!visited[i] && isValid(content, words[i])) {
                    visited[i] = true;
                    q.offer(new Word(now.cnt + 1, words[i]));
                }
            }
        }
        
        return answer;
    }
    
    private boolean isValid(String content, String word) {
        int cnt = 0;
        
        char[] c = content.toCharArray();
        char[] w = word.toCharArray();
        
        for (int i = 0; i < c.length; i++) {
            if (c[i] != w[i]) {
                cnt++;
            }
        }
        
        return cnt == 1;
    }
}