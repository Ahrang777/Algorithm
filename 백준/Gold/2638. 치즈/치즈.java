import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    static int N, M, cheeseCnt, map[][];
    static List<Node> cheese;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cheese = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    cheese.add(new Node(i, j));
                    cheeseCnt++;
                }
            }
        }

        int time = 0;

        while (cheeseCnt > 0) {
            time++;
            visited = new boolean[N][M];
            // 외부공기 처리 >> 가장자리는 치즈X
            findOutsideAir(0, 0);

            // 치즈 녹이기
            melting();
        }

        System.out.println(time);
    }

    private static void melting() {
        for (int i = 0; i < cheese.size(); i++) {
            int x = cheese.get(i).x;
            int y = cheese.get(i).y;
            int cnt = 0;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                // 주변에 외부 공기 2번 이상 접촉?
                if (map[nx][ny] == 2) {
                    cnt++;
                }
            }

            // 외부 공기 2번 이상 접촉한 경우 제거
            if (cnt >= 2) {
                map[x][y] = 0;
                cheeseCnt--;
                cheese.remove(i);
                i--;    // 현재 치즈가 제거되어 cheese의 크기가 줄어들었기에
            }
        }
    }

    private static void findOutsideAir(int x, int y) {
        visited[x][y] = true;
        map[x][y] = 2;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 1) {
                continue;
            }

            findOutsideAir(nx, ny);
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}