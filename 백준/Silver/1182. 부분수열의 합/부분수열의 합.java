import java.io.*;
import java.util.*;

public class Main {
    static int N, S, answer;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        // S = 0이면 공집합일때 포함되므로 -1 
        System.out.println(S == 0 ? answer - 1 : answer);
    }

    private static void dfs(int cnt, int total) {
        if (cnt == N) {
            if (total == S) {
                answer++;
            }
            return;
        }

        dfs(cnt + 1, total + arr[cnt]);
        dfs(cnt + 1, total);
    }
}