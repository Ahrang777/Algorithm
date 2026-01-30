import java.io.*;
import java.util.*;

public class Main {
    static int N, colorBlindCnt, nonColorBlindCnt;
    static char[][] colorBlindMap, nonColorBlindMap;
    static boolean[][] colorBlindVisited, nonColorBlindVisited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        colorBlindMap = new char[N][N];
        nonColorBlindMap = new char[N][N];
        colorBlindVisited = new boolean[N][N];
        nonColorBlindVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char ch = str.charAt(j);
                nonColorBlindMap[i][j] = ch;

                //적록색맹인 경우 R, G 하나로 통일
                if (ch == 'R') {
                    colorBlindMap[i][j] = 'G';
                } else {
                    colorBlindMap[i][j] = ch;
                }
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (!colorBlindVisited[x][y]) {
                    bfs(x, y, colorBlindMap, colorBlindVisited);
                    colorBlindCnt++;
                }

                if (!nonColorBlindVisited[x][y]) {
                    bfs(x, y, nonColorBlindMap, nonColorBlindVisited);
                    nonColorBlindCnt++;
                }
            }
        }

        sb.append(nonColorBlindCnt).append(" ").append(colorBlindCnt);
        System.out.println(sb);
    }

    private static void bfs(int x, int y, char[][] map, boolean[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            x = now[0];
            y = now[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!isRange(nx, ny) || visited[nx][ny] || map[x][y] != map[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
