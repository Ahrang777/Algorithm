import java.io.*;
import java.util.*;

public class Main {
    static int N, M, L, Q;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[M + 1];
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        arr[M] = L;

        for (int i = 0; i < N; i++) {
            Q = Integer.parseInt(br.readLine());

            int s = 0;
            int e = L;
            int answer = 0;

            while (s <= e) {
                // 가장 작은 조각
                int mid = (s + e) / 2;

                // 가장 작은 조각의 길이가 mid 일 경우 최대 조각의 수
                int cnt = 0;
                int prev = 0;

                // if 길이 >= mid cnt++, 시작점 변경
                // 자르는 지점 사이 길이
                for (int j = 0; j < arr.length; j++) {
                    int len = arr[j] - prev;

                    if (len >= mid) {
                        cnt++;
                        prev = arr[j];
                    }

                    if (cnt >= Q + 1) {
                        break;
                    }
                }

                if (cnt >= Q + 1) {
                    // 제일 작은 조각이 mid 일 경우 조각의 개수가 Q + 1 보다 크거나 같다면
                    // cnt > Q + 1인 경우는 조각 두개 합쳐서 더 크게 자르면 되기에 가능하다.
                    // 따라서 answer 값은 여기서 갱신
                    s = mid + 1;
                    answer = mid;
                } else {
                    // 제일 작은 조각이 mid 일 때 조각의 수가 Q + 1보다 작으면 더 자를 방법이 없다.
                    // 더 자를 경우 mid보다 작아질 수 있다.
                    e = mid - 1;
                }
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}