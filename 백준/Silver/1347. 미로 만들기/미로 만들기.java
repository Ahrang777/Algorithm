import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        int x = 0, y = 0, d = 2, minX = 0, minY = 0, maxX = 0, maxY = 0;

        List<int[]> positions = new ArrayList<>();

        char[] commands = br.readLine().toCharArray();
        positions.add(new int[]{0, 0});

        for (char c : commands) {
            if (c == 'F') {
                x += dx[d];
                y += dy[d];

                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);

                positions.add(new int[]{x, y});
            } else if (c == 'L') {
                d = rotateLeft(d);
            } else if (c == 'R') {
                d = rotateRight(d);
            }
        }

        int N = maxX - minX + 1, M = maxY - minY + 1;
        char[][] map = new char[N][M];

        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], '#');
        }

        for (int[] now : positions) {
            x = now[0] - minX;
            y = now[1] - minY;

            map[x][y] = '.';
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int rotateRight(int dir) {
        return (dir + 1) % 4;
    }

    private static int rotateLeft(int dir) {
        return (dir + 4 - 1) % 4;
    }
}
