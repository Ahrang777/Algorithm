import java.io.*;
import java.util.*;

public class Main{
    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, L, R;
    static int[][] people;
    static int[][] unions;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        people = new int[N][N];
        unions = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                people[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    }

    private static int solution() {
        int cnt = 0;

        while (true) {
            int index = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    unions[i][j] = -1;
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (unions[i][j] == -1) {
                        index++;
                        bfs(i, j, index);
                    }
                }
            }

            if (index == N * N) {
                break;
            }

            cnt++;
        }

        return cnt;
    }

    private static void bfs(int x, int y, int index) {
        Queue<Position> q = new LinkedList<>();
        List<Position> list = new ArrayList<>();
        q.offer(new Position(x, y));
        list.add(new Position(x, y));
        int cnt = 1;
        int sum = people[x][y];
        unions[x][y] = index;

        while (!q.isEmpty()) {
            Position now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!isRange(nx, ny) || unions[nx][ny] != -1) {
                    continue;
                }

                int diff = Math.abs(people[now.x][now.y] - people[nx][ny]);

                if (L <= diff && diff <= R) {
                    Position next = new Position(nx, ny);
                    unions[nx][ny] = index;
                    cnt++;
                    sum += people[nx][ny];
                    list.add(next);
                    q.offer(next);
                }
            }
        }

        for (Position p : list) {
            people[p.x][p.y] = sum / cnt;
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}