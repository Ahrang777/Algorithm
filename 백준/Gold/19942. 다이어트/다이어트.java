import java.io.*;
import java.util.*;

public class Main {
    static int N, MP, MF, MS, MV, answer = Integer.MAX_VALUE;
    static List<Integer> results;
    static int[][] ingredients;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        ingredients = new int[N + 1][5];
        isSelected = new boolean[N + 1];
        results = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        MP = Integer.parseInt(st.nextToken());
        MF = Integer.parseInt(st.nextToken());
        MS = Integer.parseInt(st.nextToken());
        MV = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 5; j++) {
                ingredients[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        subset(1, 0, 0, 0, 0, 0);

        if (answer == Integer.MAX_VALUE) {
            sb.append(-1);
        } else {
            sb.append(answer).append("\n");
            for (int result : results) {
                sb.append(result).append(" ");
            }
        }

        System.out.println(sb);
    }

    private static void subset(int cnt, int p, int f, int s, int v, int c) {
        // 최소 비용 찾는 것이고 굳이 끝까지 확인 안해도 중간에 최저기준 다 충족하고 비용이 더 작으면 갱신할 수 있다. 
        if (p >= MP && f >= MF && s >= MS && v >= MV && c < answer) {
            results.clear();
            for (int i = 1; i <= N; i++) {
                if (isSelected[i]) {
                    results.add(i);
                }
            }

            answer = c;
        }

        if (cnt == N + 1) {
            return;
        }

        isSelected[cnt] = true;
        subset(cnt + 1, p + ingredients[cnt][0], f + ingredients[cnt][1], s + ingredients[cnt][2], v + ingredients[cnt][3], c + ingredients[cnt][4]);
        isSelected[cnt] = false;
        subset(cnt + 1, p, f, s, v, c);
    }
}