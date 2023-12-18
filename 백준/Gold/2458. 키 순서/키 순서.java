import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] counts;
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        counts = new int[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
        }

        for (int i = 1; i <= N; i++) {
            // 그래프 탐색하면서 counts 세팅
            count(i);
        }

        int result = 0;

        for (int i = 1; i <= N; i++) {
            if (counts[i] == N - 1) {
                result++;
            }
        }

        System.out.println(result);
    }

    private static void count(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        q.offer(start);

        // start 에서 출발해서 도달한 노드들의 수를 counts[start]에 저장
        // start에서 출발해서 도달한 노드들의 경우 start에서 출발한것을 저장 counts[next]
        while (!q.isEmpty()) {
            Integer now = q.poll();

            for (int next : graph[now]) {
                if (visited[next])  continue;
                q.offer(next);
                visited[next] = true;
                
                counts[start]++;    // startd에서 나간것 추가
                counts[next]++; // next에서 start가 들어온것 추가
            }
        }
    }
}