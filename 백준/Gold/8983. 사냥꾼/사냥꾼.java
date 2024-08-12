import java.io.*;
import java.util.*;

public class Main {
    static int M, N, L, answer;
    static int[] tower;
    static int[][] animals;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        tower = new int[M];
        animals = new int[N][2];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            tower[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            animals[i][0] = Integer.parseInt(st.nextToken());
            animals[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(tower);

        for (int i = 0; i < N; i++) {
            int s = 0, e = M - 1;

            while (s <= e) {
                int mid = (s + e) / 2;
                long dist = Math.abs(tower[mid] - animals[i][0]) + animals[i][1];

                if (dist <= L) {
                    answer++;
                    break;
                }

                // 사격 범위 밖에 있는 경우
                // 동물의 좌표가 현재 사격대보다 왼쪽에 있는 경우 사격대를 왼쪽으로 옮겨야 한다.
                if (animals[i][0] < tower[mid]) {
                    e = mid - 1;
                } else {    // 동물의 좌표가 현재 사격대보다 오른쪽에 있는 경우 사격대를 오른쪽으로 옮겨야 한다.
                    s = mid + 1;
                }
            }
        }

        System.out.println(answer);
    }
}