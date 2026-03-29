import java.io.*;
import java.util.*;

public class Main {
    static int T, N;
    static String P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<Integer> dq = new ArrayDeque<>();
        boolean isReverse = false;
        boolean isError = false;

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.setLength(0);
            isReverse = false;
            isError = false;
            dq.clear();

            P = br.readLine();
            N = Integer.parseInt(br.readLine());

            if (N > 0) {
                String input = br.readLine();
                input = input.substring(1, input.length() - 1);

                String[] arr = input.split(",");

                for (int i = 0; i < N; i++) {
                    dq.offer(Integer.parseInt(arr[i]));
                }
            } else {
                br.readLine();
            }


            for (int i = 0; i < P.length(); i++) {
                char c = P.charAt(i);

                if (c == 'R') {
                    isReverse = !isReverse;
                } else {
                    if (dq.isEmpty()) {
                        sb.append("error");
                        isError = true;
                        break;
                    } else {
                        if (isReverse) {
                            dq.pollLast();
                        } else {
                            dq.pollFirst();
                        }
                    }
                }
            }

            if (!isError) {
                sb.append("[");
                while (!dq.isEmpty()) {
                    if (isReverse) {
                        sb.append(dq.pollLast());
                    } else {
                        sb.append(dq.pollFirst());
                    }

                    if (!dq.isEmpty()) {
                        sb.append(",");
                    }
                }

                sb.append("]");
            }

            System.out.println(sb);
        }
    }
}
