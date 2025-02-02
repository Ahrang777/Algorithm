import java.io.*;
import java.util.*;

public class Main {
    static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, max;
    static int[][] map, copiedMap;
    static List<Position> viruses, blanks;
    static Position[] positions;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static final int BLANK = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        copiedMap = new int[N][M];
        viruses = new ArrayList<>();
        blanks = new ArrayList<>();
        positions = new Position[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == BLANK) {
                    blanks.add(new Position(i, j));
                } else if (map[i][j] == VIRUS) {
                    viruses.add(new Position(i, j));
                }
            }
        }

        setWall(0, 0);

        System.out.println(max);
    }

    private static void setWall(int cnt, int start) {
        if (cnt == 3) {
            // 지도 복사
            copy();

            // 벽 추가
            for (Position p : positions) {
                copiedMap[p.x][p.y] = WALL;
            }

            // 바이러스 전파
            spread();

            // 안전지대 계산, 갱신
            int safeAreaCnt = countSafeArea();
            max = Math.max(max, safeAreaCnt);

            return;
        }

        for (int i = start; i < blanks.size(); i++) {
            positions[cnt] = blanks.get(i);
            setWall(cnt + 1, i + 1);
        }
    }

    private static void copy() {
        for (int i = 0; i < N; i++) {
            copiedMap[i] = map[i].clone();
        }
    }

    private static int countSafeArea() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copiedMap[i][j] == BLANK) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static void spread() {
        Queue<Position> q = new ArrayDeque<>();

        for (Position v : viruses) {
            q.offer(v);
        }

        while (!q.isEmpty()) {
            Position now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isRange(nx, ny) && copiedMap[nx][ny] == 0) {
                    copiedMap[nx][ny] = 2;
                    q.offer(new Position(nx, ny));
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}