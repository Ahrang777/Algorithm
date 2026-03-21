import java.io.*;
import java.util.*;

public class Main {
    static int N = 19;
    static int[][] map;
    static boolean isFinished;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    // 좌표가 좌상단부터 -> 방향, 끝에 도달하면 아래로 한칸 내려서 다시 좌측부터 시작하는데
    // 굳이 이전좌표 확인필요 없다. 이미 다 확인하고 넘어온것
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        map = new int[N][N];
        int result = 0;
        int x = -1, y = -1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; !isFinished && i < N; i++) {
            for (int j = 0; !isFinished && j < N; j++) {
                if (map[i][j] == 0) {
                    continue;
                }

                int target = map[i][j];

                // 좌표가 좌상단부터 -> 방향, 끝에 도달하면 아래로 한칸 내려서 다시 좌측부터 시작하는데
                // 굳이 이전좌표 확인필요 없다. 이미 다 확인하고 넘어온것
                // 0, 0 ~ 0, 18 -> 1, 0 ~ 1, 18 이런 흐름
                for (int d = 2; !isFinished && d <= 5; d++) {
                    if (isFinished(1, i, j, d)) {
                        //육목체크
                        if (isSix(i, j, d)) {
                            continue;
                        }

                        isFinished = true;
                        result = map[i][j];

                        //좌측, 상단 x, y좌표 찾기
                        if (d == 5) {   //왼쪽 아래로 향함, 끝점
                            x = i + dx[d] * 4 + 1;
                            y = j + dy[d] * 4 + 1;
                        } else {    // 시작점
                            x = i + 1;
                            y = j + 1;
                        }
                    }
                }
            }
        }

        if (isFinished) {
            sb.append(result).append("\n");
            sb.append(x).append(" ").append(y);
        } else {
            sb.append(result);
        }

        System.out.println(sb);
    }

    private static boolean isSix(int x, int y, int dir) {
        //해당 방향으로 다음좌표 육목확인
        int nx = x + dx[dir] * 5, ny = y + dy[dir] * 5;

        if (isRange(nx, ny) && map[x][y] == map[nx][ny]) {
            return true;
        }

        //반대방향
        dir = (dir + 4) % 8;
        nx = x + dx[dir];
        ny = y + dy[dir];

        //반대 방향으로 시작점 체크로 육목확인
        if (isRange(nx, ny) && map[x][y] == map[nx][ny]) {
            return true;
        }

        return false;
    }

    //오목 가능한지 확인
    private static boolean isFinished(int cnt, int x, int y, int dir) {
        if (cnt == 5) {
            return true;
        }

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (isRange(nx, ny) && map[nx][ny] == map[x][y]) {
            return isFinished(cnt + 1, nx, ny, dir);
        }

        return false;
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
