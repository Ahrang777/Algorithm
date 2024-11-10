import java.io.*;
import java.util.*;

public class Main {
    static int N, answer = 0;
    static int[][] eggs;    // 계란 순서, 0: 내구도, 1: 무게
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        eggs = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            eggs[i][0] = Integer.parseInt(st.nextToken());
            eggs[i][1] = Integer.parseInt(st.nextToken());
        }

        // 일단 시작은 무조건 0번 계란
        // 이후 선택은 나머지 다른 계란 중 알아서 선택
        dfs(0, 0);

        System.out.println(answer);
    }

    private static void dfs(int now, int total) {
        if (now == N) {
            answer = Math.max(answer, total);
            return;
        }

        // 현재 손에 든 계란이 이미 깨졌거나 더이상 깰 계란이 없는 경우 
        if (eggs[now][0] <= 0 || total == N - 1) {
            dfs(now + 1, total);
        } else {
            for (int i = 0; i < N; i++) {
                // 현재 들고있는 계란인 경우
                if (i == now) {
                    continue;
                }

                // 0: 내구도, 1: 무게
                // 해당 계란이 이미 깨진 경우
                if (eggs[i][0] <= 0) {
                    continue;
                }

                int nowTotal = total;

                // 계란 치기
                int[] egg = eggs[now];
                int[] target = eggs[i];

                int nowD = egg[0];
                int targetD = target[0];

                egg[0] -= target[1];
                target[0] -= egg[1];

                // 손에 든 계란이 깨진 경우
                if (egg[0] <= 0) {
                    total++;
                }

                // 대상 계란이 깨진 경우
                if (target[0] <= 0) {
                    total++;
                }

                dfs(now + 1, total);

                // 원상복구
                egg[0] = nowD;
                target[0] = targetD;
                total = nowTotal;
            }
        }
    }
}