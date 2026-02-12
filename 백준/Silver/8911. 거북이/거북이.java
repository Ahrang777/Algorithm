import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String input = br.readLine();
            char[] commands = input.toCharArray();

            List<int[]> list = new ArrayList<>();

            int x = 0, y = 0, d = 0;
            int minX = x, minY = y, maxX = x, maxY = y;
            list.add(new int[]{x, y});

            for (char c : commands) {
                if (c == 'F') {
                    x += dx[d];
                    y += dy[d];
                } else if (c == 'B') {
                    x -= dx[d];
                    y -= dy[d];
                } else if (c == 'L') {
                    d = (d + 4 - 1) % 4;
                } else if (c == 'R') {
                    d = (d + 1) % 4;
                }

                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);
            }

            int area = (maxX - minX) * (maxY - minY);

            sb.append(area).append("\n");
        }

        System.out.println(sb);
    }
}
