import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int index, dist;

        public Node(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }
    }
    static int N, M, F1, F2, s, e;
    static List<Node>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        s = 0;
        e = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[A].add(new Node(B, dist));
            graph[B].add(new Node(A, dist));
            e = Math.max(e, dist);
        }

        st = new StringTokenizer(br.readLine());
        F1 = Integer.parseInt(st.nextToken());
        F2 = Integer.parseInt(st.nextToken());

        int answer = 0;
        while (s <= e) {
            int mid = (s + e) / 2;

            // 이동 가능
            if (isValid(mid)) {
                answer = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean isValid(int dist) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        q.offer(F1);
        visited[F1] = true;

        while (!q.isEmpty()) {
            Integer now = q.poll();

            if (now == F2) {
                return true;
            }

            for (Node next : graph[now]) {
                int index = next.index;
                int limit = next.dist;

                if (visited[index] || dist > limit) {
                    continue;
                }

                visited[index] = true;
                q.offer(index);
            }
        }

        return false;
    }
}