import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] table;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        table = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                table[i][j] = str.charAt(j);
            }
        }

        int answer = 0;

        int s = 0, e = R - 1;

        while (s <= e) {
            // 지워지는 행의 번호
            int mid = (s + e) / 2;

            if (isValid(mid)) {
                answer = mid + 1;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean isValid(int index) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < C; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = index + 1; j < R; j++) {
                sb.append(table[j][i]);
            }

            if (set.contains(sb.toString())) {
                return false;
            }

            set.add(sb.toString());
        }

        return true;
    }
}