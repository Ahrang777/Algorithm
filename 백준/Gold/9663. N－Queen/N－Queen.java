import java.io.*;
import java.util.*;

public class Main {
    static int N, cnt;
    static int[] queen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        queen = new int[N];

        dfs(0);

        System.out.println(cnt);
    }

    private static void dfs(int x) {
        if (x == N) {
            cnt++;
            return;
        }

        for (int y = 0; y < N; y++) {
            queen[x] = y;

            if (isPossible(x)) {
                dfs(x + 1);
            }
        }
    }

    private static boolean isPossible(int x) {
        for (int i = 0; i < x; i++) {
            if (queen[i] == queen[x] || x - i == Math.abs(queen[x] - queen[i])) {
                return false;
            }
        }

        return true;
    }
}
