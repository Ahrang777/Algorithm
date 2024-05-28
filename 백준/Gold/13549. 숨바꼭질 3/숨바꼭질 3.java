import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int index, time;

        public Node(int index, int time) {
            this.index = index;
            this.time = time;
        }
    }
    static int N, K;
    static int max = 100000;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs(N, K);
        System.out.println(min);
    }

    private static void bfs(int now, int target) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[max + 1];

        q.offer(new Node(now, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            int current = node.index;
            int time = node.time;
            visited[current] = true;

            if (current == target) {
                min = Math.min(min, time);
            }

            if (current - 1 >= 0 && !visited[current - 1]) {
                q.offer(new Node(current - 1, time + 1));
            }
            if (current + 1 <= max && !visited[current + 1]) {
                q.offer(new Node(current + 1, time + 1));
            }
            if (2 * current <= max && !visited[2 * current]) {
                q.offer(new Node(2 * current, time));
            }
        }
    }
}