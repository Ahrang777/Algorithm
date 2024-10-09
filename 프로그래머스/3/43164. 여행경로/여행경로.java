import java.util.*;
class Solution {
    static int N;
    static String result;
    static final String SEP = " ";    
    public String[] solution(String[][] tickets) {
        N = tickets.length;
        StringBuilder sb = new StringBuilder();
        sb.append("ICN").append(SEP);
        boolean[] isSelected = new boolean[tickets.length];
        
        dfs(0, tickets, isSelected, sb);
        
        return result.split(SEP);
    }
    
    private void dfs(int cnt, String[][] tickets, boolean[] isSelected, StringBuilder sb) {
        if (cnt == N) {
            String tmp = sb.toString();
            
            if (result == null) {
                result = tmp;
            } else if (tmp.compareTo(result) < 0) {
                result = tmp;
            }
            
            return;
        }
        
        for (int i = 0; i < N; i++) {
            if (isSelected[i])  continue;
            
            int size = sb.length();
            String from = sb.substring(size - 4, size - 1);
            
            if (tickets[i][0].equals(from)) {
                isSelected[i] = true;
                sb.append(tickets[i][1]).append(SEP);
                dfs(cnt + 1, tickets, isSelected, sb);
                sb.delete(sb.length() - 4, sb.length());
                isSelected[i] = false;
            }
        }
    }
}