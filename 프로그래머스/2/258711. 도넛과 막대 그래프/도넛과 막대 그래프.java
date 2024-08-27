class Solution {
    static final int N = 1_000_001;
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        int[] in = new int[N];
        int[] out = new int[N];
        
        int max = 0;
        for (int[] e : edges) {
            int from = e[0];
            int to = e[1];
            
            in[to]++;
            out[from]++;
            max = Math.max(max, Math.max(from, to));
        }
        
        int created = 0;
        int stick = 0;
        int eight = 0;
        for (int i = 1; i <= max; i++) {                        
            if (out[i] >= 2) {
                if (in[i] == 0) {
                    created = i;
                } else {
                    eight++;
                }
            } else if (out[i] == 0 && in[i] > 0) {
                stick++;
            }
        }
        
        answer[0] = created;
        answer[1] = out[created] - (stick + eight);
        answer[2] = stick;
        answer[3] = eight;
        
        return answer;
    }
}