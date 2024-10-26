import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static int[] selected;
    static int answer;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[5][5];
        selected = new int[7];

        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        dfs(0, 0, 0);

        System.out.println(answer);
    }

    private static void dfs(int cnt, int start, int cntY) {
        // 이다솜파 적어도 4명 이상
        if (cntY >= 4) {
            return;
        }

        if (cnt == 7) {
            if (isValid()) {
                answer++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            selected[cnt] = i;

            if (map[i / 5][i % 5] == 'Y') {
                dfs(cnt + 1, i + 1, cntY + 1);
            } else {
                dfs(cnt + 1, i + 1, cntY);
            }
        }
    }

    private static boolean isValid() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[7];

        q.offer(new int[]{selected[0] / 5, selected[0] % 5});
        visited[0] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isRange(nx, ny)) {
                    continue;
                }

                for (int j = 0; j < 7; j++) {
                    if (!visited[j] && selected[j] == nx * 5 + ny) {
                        q.offer(new int[]{nx, ny});
                        visited[j] = true;
                        cnt++;
                    }
                }
            }
        }


        return cnt == 7;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }
}