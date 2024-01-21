import java.io.*;
import java.util.*;

public class Main{
    static class Moving{
        int d, s;

        public Moving(int d, int s) {
            this.d = d;
            this.s = s;
        }
    }

    static class Cloud {
        int x, y;

        public Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int n, m;
    
    // 물 양
    public static int[][] map;

    // 비 내린 지역
    public static boolean[][] visited;

    // 이동
    public static ArrayList<Moving> movings = new ArrayList<>();

    // 구름
    public static ArrayList<Cloud> clouds;

    // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    public static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    public static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void solution() {
        for (Moving m : movings) {

            int d = m.d;
            int s = m.s;

            // 비 내린 지역 초기화
            visited = new boolean[n][n];

            // 구름 이동, 비 내림
            move(d, s);

            // 물복사 버그 마법
            copyMagic();

            // 구름 생성
            createCloud();
        }
        
        // 전체 물의 양
        getTotal();
    }

    public static void getTotal() {
        int total = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                total += map[i][j];
            }
        }

        System.out.println(total);
    }

    public static void createCloud() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] >= 2 && !visited[i][j]) {
                    map[i][j] -= 2;
                    clouds.add(new Cloud(i, j));
                }
            }
        }
    }

    public static void copyMagic() {

        // 비 내린 지역
        for (Cloud c : clouds) {
            int cnt = 0;
            // 대각선
            // 1, 3, 5, 7
            for (int i = 1; i < 8; i += 2) {
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];

                if ((nx >= 0 && nx < n && ny >= 0 && ny < n) && (map[nx][ny] > 0)) {
                    cnt++;
                }
            }

            map[c.x][c.y] += cnt;
        }

        clouds.clear();
    }

    // d 방향 s칸 이동
    public static void move(int d, int s) {
        for (Cloud c : clouds) {
            // 구름 이동
            int nx = (c.x + n + s * dx[d] % n) % n;
            int ny = (c.y + n + s * dy[d] % n) % n;

            // 비내림
            map[nx][ny] += 1;
            visited[nx][ny] = true;
            c.x = nx;
            c.y = ny;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(bf.readLine());

            int d = Integer.parseInt(stk.nextToken()) - 1;
            int s = Integer.parseInt(stk.nextToken());

            movings.add(new Moving(d, s));
        }

        clouds = new ArrayList<>();
        clouds.add(new Cloud(n - 1, 0));
        clouds.add(new Cloud(n - 1, 1));
        clouds.add(new Cloud(n - 2, 0));
        clouds.add(new Cloud(n - 2, 1));

        solution();
    }
}