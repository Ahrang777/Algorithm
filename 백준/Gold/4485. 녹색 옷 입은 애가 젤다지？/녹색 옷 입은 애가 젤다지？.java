import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    static int N, min;
    static int[][] map, d;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static final int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        for (int tc = 1;; tc++) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) {
                break;
            }

            min = Integer.MAX_VALUE;
            map = new int[N][N];
            d = new int[N][N];

            for (int i = 0; i < N; i++) {
                Arrays.fill(d[i], INF);
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra();

            sb.append("Problem ").append(tc).append(": ");
            sb.append(min).append("\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.dist, o2.dist));
        pq.offer(new Node(0, 0, map[0][0]));
        d[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int x = node.x, y = node.y;
            int dist = node.dist;

            if (x == N - 1 && y == N - 1) {
                min = Math.min(min, dist);
                return;
            }

            if (d[x][y] < dist) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isRange(nx, ny)) {
                    continue;
                }

                int cost = d[x][y] + map[nx][ny];
                if (cost < d[nx][ny]) {
                    d[nx][ny] = cost;
                    pq.offer(new Node(nx, ny, cost));
                }
            }
        }

    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}