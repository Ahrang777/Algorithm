import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static class Robot {
        int x, y, dir;

        public Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    static int A, B, N, M;
    static int[][] map;
    static Map<Integer, Robot> robots;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        map = new int[B][A];
        robots = new HashMap<>();

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // map[y][x] 형태로 작성
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;

            char ch = st.nextToken().charAt(0);

            int dir = getDir(ch);
            map[x][y] = i;
            robots.put(i, new Robot(x, y, dir));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int robotNumber = Integer.parseInt(st.nextToken());
            char command = st.nextToken().charAt(0);
            int repeat = Integer.parseInt(st.nextToken());

            Robot now = robots.get(robotNumber);
            

            // 앞으로 한칸 이동
            if (command == 'F') {
                int nx = now.x;
                int ny = now.y;

                for (int j = 0; j < repeat; j++) {
                    nx += dx[now.dir];
                    ny += dy[now.dir];

                    if (!isRange(nx, ny)) {
                        System.out.println("Robot " + robotNumber + " crashes into the wall");
                        return;
                    }

                    if (isCrashed(nx, ny)) {
                        System.out.println("Robot " + robotNumber + " crashes into robot " + map[nx][ny]);
                        return;
                    }
                }
                map[now.x][now.y] = 0;
                map[nx][ny] = robotNumber;
                robots.put(robotNumber, new Robot(nx, ny, now.dir));
            } else {    // 방향전환
                int dir = now.dir;
                for (int j = 0; j < repeat; j++) {
                    dir = rotate(dir, command);
                }
                robots.put(robotNumber, new Robot(now.x, now.y, dir));
            }
        }

        System.out.println("OK");
    }

    private static int rotate(int dir, char rotateDir) {
        if (rotateDir == 'L') {
            dir = (dir + 3) % 4;
        }

        if (rotateDir == 'R') {
            dir = (dir + 1) % 4;
        }

        return dir;
    }

    private static boolean isCrashed(int x, int y) {
        return map[x][y] != 0;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < B && y >= 0 && y < A;
    }

    private static int getDir(char ch) {
        if (ch == 'N') {
            return 0;
        } else if (ch == 'E') {
            return 1;
        } else if (ch == 'S') {
            return 2;
        } else {
            return 3;
        }
    }
}