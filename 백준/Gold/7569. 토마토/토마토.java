import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int x, y, h, time;

        public Node(int x, int y, int h, int time) {
            this.x = x;
            this.y = y;
            this.h = h;
            this.time = time;
        }
    }
    static int N, M, H, total, result;
    static int[][][] map;
    static List<Node> list;
    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dh = {0, 0, 0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N][M];
        list = new ArrayList<>();
        total = N * M * H;

        for (int h = 0; h < H; h++) {
            for (int x = 0; x < N; x++) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0; y < M; y++) {
                    map[h][x][y] = Integer.parseInt(st.nextToken());

                    if (map[h][x][y] == 1) {
                        list.add(new Node(x, y, h, 0));
                    } else if (map[h][x][y] == -1) {
                        total--;
                    }
                }
            }
        }

        bfs();

        System.out.println(total == 0 ? result : -1);
    }

    private static void bfs() {
        Queue<Node> q = new ArrayDeque<>();

        for (Node node : list) {
            q.offer(node);
        }

        while (!q.isEmpty()) {
            Node now = q.poll();
            total--;

            result = Math.max(result, now.time);

            for (int i = 0; i < 6; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                int nh = now.h + dh[i];

                if (!isRange(nx, ny, nh) || map[nh][nx][ny] != 0) {
                    continue;
                }

                q.offer(new Node(nx, ny, nh, now.time + 1));
                map[nh][nx][ny] = 1;
            }
        }
    }

    private static boolean isRange(int x, int y, int h) {
        return x >= 0 && x < N && y >= 0 && y < M && h >= 0 && h < H;
    }
}
