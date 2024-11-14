import java.io.*;
import java.util.*;

public class Main {
    static int K, N, F;
    static int[] indegree;
    static int[][] arr;
    static boolean[] visited;
    static boolean isFinished = false;
    static String answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];    // 친구 관계, 인접행렬
        indegree = new int[N + 1];  // 친구 수
        visited = new boolean[N + 1];   // 소풍 갈 대상

        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            arr[v1][v2] = arr[v2][v1] = 1;
            indegree[v1]++;
            indegree[v2]++;
        }

        for (int i = 1; i <= N; i++) {
            // 본인 포함 K명 >> 현재 indegree는 본인을 제외한 친구 수
            if (indegree[i] < K - 1) {
                continue;
            }

            if (isFinished) {
                break;
            }

            visited[i] = true;
            dfs(i, 1);
            visited[i] = false;
        }

        if (isFinished) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }

    private static void dfs(int now, int cnt) {
        if (isFinished) {
            return;
        }

        if (cnt == K) {
            answer = print();
            isFinished = true;
            return;
        }

        for (int i = now + 1; i <= N; i++) {
            // 현재 학생 now, 대상 학생 i가 친구
            // 이전까지 서로 친구를 확인한 학생들과 대상 학생 i가 친구
            if (arr[now][i] == 1 && isFriend(i)) {
                visited[i] = true;
                dfs(i, cnt + 1);
                visited[i] = false;
            }
        }
    }

    private static String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                sb.append(i).append("\n");
            }
        }
        return sb.toString();
    }

    private static boolean isFriend(int target) {
        for (int i = 1; i <= N; i++) {
            if (visited[i] && arr[target][i] != 1) {
                return false;
            }
        }

        return true;
    }
}