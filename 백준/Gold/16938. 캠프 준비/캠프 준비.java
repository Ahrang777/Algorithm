import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R, X, answer;
    static int[] arr;
    public static void main(String[] args) throws IOException {        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);

        System.out.println(answer);
    }

    private static void dfs(int cnt, int total, int max, int min) {
        if (total > R) {
            return;
        }

        if (cnt == N) { // 마지막까지 모두 확인
            // 2개 이상 선택x
            if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
                return;
            }

            // L <= total <= R
            // 가장 어려운 난이도 - 가장 쉬운 난이도 >= X
            if (max - min >= X && total >= L) {
                answer++;
            }

            return;
        }

        dfs(cnt + 1, total + arr[cnt], Math.max(max, arr[cnt]), Math.min(min, arr[cnt]));
        dfs(cnt + 1, total, max, min);
    }
}