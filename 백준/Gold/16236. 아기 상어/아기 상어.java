import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Position implements Comparable<Position> {
        int x, y, dist;

        public Position(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Position o) {
            if (this.dist != o.dist) {
                return Integer.compare(this.dist, o.dist);
            } else if (this.x != o.x) {
                return Integer.compare(this.x, o.x);
            } else {
                return Integer.compare(this.y, o.y);
            }
        }
    }

    static int N, result;
    static int[][] map;
    static int sharkSize = 2, eat = 0;
    static int sx, sy;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        result = 0;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sx = i;
                    sy = j;
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            // 이동 가능 여부
            // bfs boolean 반환
            // false이면 종료
            if (!bfs()) {
                break;
            }
        }

        System.out.println(result);
    }

    private static boolean bfs() {
        // 다음에 먹을 물고기 후보들 중 선택하기 위한 우선순위 큐
        PriorityQueue<Position> pq = new PriorityQueue<>();
        // bfs로 먹을 물고기 후보들 탐색
        Queue<Position> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        q.offer(new Position(sx, sy, 0));
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            Position now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!isRange(nx, ny) || visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] <= sharkSize) {
                    visited[nx][ny] = true;
                    q.offer(new Position(nx, ny, now.dist + 1));
                    if (map[nx][ny] != 0 && map[nx][ny] < sharkSize) {
                        pq.offer(new Position(nx, ny, now.dist + 1));
                    }
                }
            }
        }

        // 먹을 수 있는 물고기 후보 없는 경우
        if (pq.isEmpty()) {
            return false;
        }

        Position target = pq.poll();
        result += target.dist;
        sx = target.x;
        sy = target.y;

        map[sx][sy] = 0;
        eat(sx, sy);

        return true;
    }

    private static void eat(int x, int y) {
        eat++;
        if (eat == sharkSize) {
            eat = 0;
            sharkSize++;
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}