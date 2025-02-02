import java.io.*;
import java.util.*;

public class Main {
    static int N, result;
    static int[] queens;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        queens = new int[N];       

        dfs(0);

        System.out.println(result);
    }

    private static void dfs(int cnt) {
        if (cnt == N) {
            result++;
            return;
        }

        for (int i = 0; i < N; i++) {
            queens[cnt] = i;
            if (isValid(cnt)) {
                dfs(cnt + 1);
            }
        }
    }

    // cnt번째에 놓은 퀸과 이전 퀸들 비교
    private static boolean isValid(int cnt) {
        for (int i = 0; i < cnt; i++) {
            if (queens[i] == queens[cnt] || cnt - i == Math.abs(queens[i] - queens[cnt])) {
                return false;
            }
        }

        return true;
    }
}