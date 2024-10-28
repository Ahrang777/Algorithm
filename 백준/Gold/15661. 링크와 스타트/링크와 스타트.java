import java.io.*;
import java.util.*;

public class Main {
    static int N, answer;
    static int[][] map;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        answer = Integer.MAX_VALUE;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        isSelected = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(answer);
    }

    private static void dfs(int cnt) {
        if (cnt == N) {
            int start = 0, link = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    if (isSelected[i] == isSelected[j]) {
                        if (isSelected[i]) {
                            start += (map[i][j] + map[j][i]);
                        } else {
                            link += (map[i][j] + map[j][i]);
                        }
                    }
                }
            }

            answer = Math.min(answer, Math.abs(start - link));
            return;
        }

        isSelected[cnt] = true;
        dfs(cnt + 1);
        isSelected[cnt] = false;
        dfs(cnt + 1);
    }
}