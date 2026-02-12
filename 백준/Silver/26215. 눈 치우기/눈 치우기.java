import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int result = 0;

        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            pq.offer(num);
        }


        if (pq.peek() > 1440) {
            result = -1;
        } else {
            while (pq.size() >= 1) {
                result++;


                if (pq.size() >= 2) {
                    int num1 = pq.poll();
                    int num2 = pq.poll();

                    num1--;
                    num2--;

                    if (num1 > 0) {
                        pq.offer(num1);
                    }

                    if (num2 > 0) {
                        pq.offer(num2);
                    }
                } else {
                    int num = pq.poll();
                    num--;
                    if (num > 0) {
                        pq.offer(num);
                    }
                }
            }

        }

        System.out.println(result > 1440 ? -1 : result);
    }
}
