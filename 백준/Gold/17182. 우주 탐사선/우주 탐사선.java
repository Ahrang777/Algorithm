import java.io.*;
import java.util.*;

public class Main {
    static int N, K, answer = Integer.MAX_VALUE;
    static int[][] T;
    static boolean[] visited;
    static int[] output;
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        T = new int[N][N];
        visited = new boolean[N];
        output = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                T[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int a = 0; a < N; a++) {
                for (int b = 0; b < N; b++) {
                    T[a][b] = Math.min(T[a][b], T[a][i] + T[i][b]);
                }
            }
        }

        visited[K] = true;
        output[0] = K;
        permutation(1);

        System.out.println(answer);
    }

    private static int calculate() {
        int total = T[K][output[1]];

        for (int i = 1; i < N - 1; i++) {
            total += T[output[i]][output[i + 1]];
        }

        return total;
    }

    private static void permutation(int cnt) {
        if (cnt == N) {
            int time = calculate();
            answer = Math.min(answer, time);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            output[cnt] = i;
            permutation(cnt + 1);
            visited[i] = false;
        }
    }
}