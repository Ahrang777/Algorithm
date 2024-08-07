import java.io.*;
import java.util.*;

public class Main {
    static int T, N;
    static List<Integer> aList, bList;
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        aList = new ArrayList<>();
        bList = new ArrayList<>();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            graph = new List[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                graph[child].add(parent);
            }

            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            aList.clear();
            bList.clear();

            dfs(A, aList);
            dfs(B, bList);

            for (int a : aList) {
                if (bList.contains(a)) {
                    sb.append(a).append("\n");
                    break;
                }
            }
        }

        System.out.println(sb);
    }

    private static void dfs(int now, List<Integer> parents) {
        parents.add(now);

        for (int next : graph[now]) {
            dfs(next, parents);
        }
    }
}