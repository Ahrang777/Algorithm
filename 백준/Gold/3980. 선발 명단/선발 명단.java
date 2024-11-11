import java.io.*;
import java.util.*;

public class Main {
    static int answer;
    static int[][] players;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            answer = Integer.MIN_VALUE;
            players = new int[11][11];
            isSelected = new boolean[11];

            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < 11; j++) {
                    players[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, 0);

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    /**
     *
     * @param cnt 포지션 번호
     * @param total 능력치 합
     */
    private static void dfs(int cnt, int total) {
        if (cnt == 11) {
            answer = Math.max(answer, total);
            return;
        }

        // 능력치 0인 포지션 배치x
        // 각 선수마다 적합한 포지션 최대 5개 (능력치가 0보다 큰 경우)
        for (int i = 0; i < 11; i++) {
            // 이미 뽑혔거나 해당 포지션에 적합하지 않은 경우
            if (isSelected[i] || players[i][cnt] == 0) {
                continue;
            }

            isSelected[i] = true;
            dfs(cnt + 1, total + players[i][cnt]);
            isSelected[i] = false;
        }
    }
}