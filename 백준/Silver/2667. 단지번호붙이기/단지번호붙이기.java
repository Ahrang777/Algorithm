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
    static boolean[][] visited;
    static List<Integer> counts;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        counts = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || map[i][j] == 0)  continue;

                bfs(i, j);
            }
        }

        Collections.sort(counts);
        sb.append(counts.size()).append("\n");

        for (Integer cnt : counts) {
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new ArrayDeque<>();
        visited[x][y] = true;
        q.offer(new Node(x, y));
        int cnt = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int curX = node.x;
            int curY = node.y;
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 0) continue;

                visited[nx][ny] = true;
                q.offer(new Node(nx, ny));
            }
        }

        counts.add(cnt);
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}