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

        int s = 0;
        int e = (int) 1e9;
        int answer = e;

        while (s <= e) {
            // 최대 경사
            int mid = (s + e) / 2;

            if (isValid(mid)) { // 최대경사 mid일 경우 N, N에 도달할 수 있는지 여부
                e = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                s = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean isValid(int max) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        q.offer(new Node(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            int x = now.x, y = now.y;

            if (x == N - 1 && y == N - 1) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isRange(nx, ny) || visited[nx][ny] || Math.abs(map[nx][ny] - map[x][y]) > max) {
                    continue;
                }

                visited[nx][ny] = true;
                q.offer(new Node(nx, ny));
            }
        }

        return false;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}