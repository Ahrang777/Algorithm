import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node node) {
            return Integer.compare(this.dist, node.dist);
        }
    }
    static int N, answer;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println(answer);
    }

    private static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            // 최소 경사를 뽑고 해당 위치 방문처리 
            // >> 애초에 넣을 때부터 방문안 한것만 넣기에 그냥 방문처리하면됨
            // 매번 경사 최댓값 갱신
            // 도착지점에 도착하면 종료
            Node now = pq.poll();
            int x = now.x, y = now.y, dist = now.dist;
            visited[x][y] = true;
            answer = Math.max(answer, dist);
            if (x == N - 1 && y == N - 1) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isRange(nx, ny) || visited[nx][ny]) {
                    continue;
                }

                pq.offer(new Node(nx, ny, Math.abs(map[nx][ny] - map[x][y])));
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}