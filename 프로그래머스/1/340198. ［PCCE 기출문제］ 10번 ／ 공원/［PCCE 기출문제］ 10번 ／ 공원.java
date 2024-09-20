import java.util.*;
class Solution {
    static int N, M;
    public int solution(int[] mats, String[][] park) {
        int answer = -1;
        N = park.length;
        M = park[0].length;
        
        Arrays.sort(mats);
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!park[i][j].equals("-1"))   continue;
                
                for (int k = mats.length - 1; k >= 0; k--) {
                    int mat = mats[k];
                    if (answer >= mat)  break;
                    
                    if (isValid(i, j, mat, park)) {
                        answer = mat;
                    }
                }
            }
        }
        
        return answer;
    }
    
    private boolean isValid(int i, int j, int mat, String[][] park) {
        if (i + mat > N || j + mat > M) return false;
        
        for (int x = i; x < i + mat; x++) {
            for (int y = j; y < j + mat; y++) {
                if (!park[x][y].equals("-1")) {
                    return false;
                }
            }
        }
        
        return true;
    }
}