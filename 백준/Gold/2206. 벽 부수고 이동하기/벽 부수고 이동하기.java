import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
    static class Node {
        int x, y, brokenWallCnt;

        public Node(int x, int y, int brokenWallCnt) {
            this.x = x;
            this.y = y;
            this.brokenWallCnt = brokenWallCnt;
        }
    }
    static int N, M, min, map[][], v[][][];

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new ArrayDeque<>();
        int[][][] v = new int[2][N][M];   // 벽 부순 개수, x, y
        q.offer(new Node(x, y, 0));


        v[0][x][y] = 1;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == N - 1 && now.y == M - 1) {
                min = v[now.brokenWallCnt][now.x][now.y];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!isRange(nx, ny)) {
                    continue;
                }

                // 다음이 벽, 아직 벽을 부순적 없는 경우
                if (map[nx][ny] == 1 && now.brokenWallCnt == 0) {
                    q.offer(new Node(nx, ny, 1));
                    v[1][nx][ny] = v[0][now.x][now.y] + 1;
                }

                // 다음이 빈칸
                if (map[nx][ny] == 0 && v[now.brokenWallCnt][nx][ny] == 0) {
                    q.offer(new Node(nx, ny, now.brokenWallCnt));
                    v[now.brokenWallCnt][nx][ny] = v[now.brokenWallCnt][now.x][now.y] + 1;
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}