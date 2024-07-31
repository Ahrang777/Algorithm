import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int[][] map;
    static int[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static final int B = 0;
    static final int W = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0);

        System.out.println(visited[N - 1][N - 1] - 1);
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new ArrayDeque<>();
        visited = new int[N][N];

        q.offer(new Node(x, y));
        visited[x][y] = map[x][y] == B ? 2 : 1;

        while (!q.isEmpty()) {
            Node now = q.poll();
            int nowX = now.x;
            int nowY = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];

                if (!isRange(nx, ny)) {
                    continue;
                }

                if (map[nx][ny] == W) { // 흰색
                    if (visited[nx][ny] == 0) { // 첫 방문
                        visited[nx][ny] = visited[nowX][nowY];
                        q.offer(new Node(nx, ny));
                    } else {    // 앞서 반복한적 있는 경우
                        if (visited[nx][ny] > visited[nowX][nowY]) {
                            visited[nx][ny] = visited[nowX][nowY];
                            q.offer(new Node(nx, ny));
                        }
                    }
                } else {    // 검정색
                    if (visited[nx][ny] == 0) { // 첫 방문
                        visited[nx][ny] = visited[nowX][nowY] + 1;
                        q.offer(new Node(nx, ny));
                    } else {    // 앞서 반복한적 있는 경우
                        if (visited[nx][ny] > visited[nowX][nowY] + 1) {
                            visited[nx][ny] = visited[nowX][nowY] + 1;
                            q.offer(new Node(nx, ny));
                        }
                    }
                }

            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}