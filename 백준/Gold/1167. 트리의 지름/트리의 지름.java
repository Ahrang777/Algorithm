import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int index, dist;

        public Node(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }
    }
    static int V, max;
    static List<Node>[] graph;
    static boolean[] visited;
    static int node;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        V = Integer.parseInt(br.readLine());
        max = Integer.MIN_VALUE;

        graph = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int v = 0; v < V; v++) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());

            while (true) {
                int next = Integer.parseInt(st.nextToken());

                if (next == -1) break;

                int dist = Integer.parseInt(st.nextToken());

                graph[index].add(new Node(next, dist));
            }
        }

        // 임의의 노드에서 가장 먼 노드찾기
        // 트리에서 한 노드에서 다른 노드까지 경로는 유일하다
        // 때문에 한 노드에서 제일 먼 노드(dist로 비교)는 트리의 지름을 구성하는 시작, 끝 노드 중 하나에 해당한다.
        visited = new boolean[V + 1];
        dfs(1, 0);

        // 찾은 노드로 부터 가장 먼 노드 찾기
        // 그 과정에서 max갱신
        visited = new boolean[V + 1];
        dfs(node, 0);
        System.out.println(max);
    }

    private static void dfs(int now, int total) {
        // 리프 노드인 경우
        // 정답 체크
        if (total > max) {
            max = total;
            node = now;
        }
        visited[now] = true;

        for (Node next : graph[now]) {
            if (visited[next.index])    continue;

            dfs(next.index, next.dist + total);
        }
    }
}