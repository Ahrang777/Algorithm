import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static StringBuilder sb;
    static List<String>[] results;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        results = new List[T];

        for (int tc = 0; tc < T; tc++) {
            results[tc] = new ArrayList<>();
            N = Integer.parseInt(br.readLine());

            dfs(tc, 1, new int[N]);
            Collections.sort(results[tc]);
        }

        sb.setLength(0);
        for (List<String> result : results) {
            for (String s : result) {
                sb.append(s);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int tc, int cnt, int[] op) {
        if (cnt == N) {
            Deque<Integer> dq = new ArrayDeque<>();
            dq.offer(1);

            // ' ' 처리
            for (int i = 1; i < N; i++) {
                if (op[i] == 2) {
                    int now = dq.pollLast();
                    now = now * 10 + i + 1;
                    dq.offerLast(now);
                    continue;
                }

                dq.offerLast(i + 1);
            }

            int result = dq.pollFirst();

            for (int i = 1; i < N; i++) {
                if (op[i] == 2) {
                    continue;
                }

                int next = dq.pollFirst();
                if (op[i] == 0) {   // +
                    result += next;
                } else {    // -
                    result -= next;
                }
            }

            if (result == 0) {
                sb.setLength(0);

                sb.append(1);
                for (int i = 1; i < N; i++) {
                    if (op[i] == 0) {
                        sb.append("+").append(i + 1);
                    } else if (op[i] == 1) {
                        sb.append("-").append(i + 1);
                    } else {
                        sb.append(" ").append(i + 1);
                    }
                }
                sb.append("\n");

                results[tc].add(sb.toString());
            }

            return;
        }

        // +, -, ' '
        for (int i = 0; i < 3; i++) {
            op[cnt] = i;
            dfs(tc, cnt + 1, op);
        }
    }
}