import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int h, x, y, time;

        public Node(int h, int x, int y, int time) {
            this.h = h;
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static int M, N, H, map[][][], unripeCnt, min;

    static List<Node> list;

    // h, x, y
    static int[][] d = {
            {-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 0, -1}, {0, 1, 0}, {0, 0, 1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N][M];
        list = new ArrayList<>();
        min = Integer.MAX_VALUE;

        for (int h = 0; h < H; h++) {
            for (int x = 0; x < N; x++) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0; y < M; y++) {
                    map[h][x][y] = Integer.parseInt(st.nextToken());

                    // 익지 않은 토마토
                    if (map[h][x][y] == 0) {
                        unripeCnt++;
                    }

                    // 익은 토마토
                    if (map[h][x][y] == 1) {
                        list.add(new Node(h, x, y, 0));
                    }
                }
            }
        }

        bfs();

        System.out.println(unripeCnt == 0 ? min : -1);
    }

    private static void bfs() {
        Queue<Node> q = new ArrayDeque<>();

        for (Node node : list) {
            q.offer(node);
        }

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (int i = 0; i < 6; i++) {
                int nh = now.h + d[i][0];
                int nx = now.x + d[i][1];
                int ny = now.y + d[i][2];

                min = now.time;

                if (!isRange(nh, nx, ny) || map[nh][nx][ny] != 0)    continue;

                unripeCnt--;
                map[nh][nx][ny] = 1;
                q.offer(new Node(nh, nx, ny, now.time + 1));
            }
        }
    }

    private static boolean isRange(int h, int x, int y) {
        return h >= 0 && h < H && x >= 0 && x < N && y >= 0 && y < M;
    }
}