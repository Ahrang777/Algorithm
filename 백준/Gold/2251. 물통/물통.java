import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int a, b, c;

        public Node(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    static int A, B, C;
    static List<Integer> results;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        results = new ArrayList<>();
        visited = new boolean[201][201][201];

        bfs(0, 0, C);
        Collections.sort(results);

        for (Integer result : results) {
            sb.append(result).append(" ");
        }

        System.out.println(sb);
    }

    private static void bfs(int a, int b, int c) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(a, b, c));

        while (!q.isEmpty()) {
            Node now = q.poll();
            int curA = now.a;
            int curB = now.b;
            int curC = now.c;

            if (visited[curA][curB][curC]) {
                continue;
            }

            visited[curA][curB][curC] = true;

            if (curA == 0) {
                results.add(curC);
            }

            // 물 옮기는 경우 >> 넘치는 경우 or 넘치지 않는 경우
            // A -> B
            if (curA + curB >= B) { // 옮기면 물이 넘치는 경우
                q.offer(new Node(curA - (B - curB), B, curC));
            } else {    // 옮겨도 물이 넘치지 않는 경우
                q.offer(new Node(0, curA + curB, curC));
            }

            // A -> C
            if (curA + curC >= C) {
                q.offer(new Node(curA - (C - curC), curB, C));
            } else {
                q.offer(new Node(0, curB, curA + curC));
            }

            // B -> A
            if (curB + curA >= A) {
                q.offer(new Node(A, curB - (A - curA), curC));
            } else {
                q.offer(new Node(curA + curB, 0, curC));
            }

            // B -> C
            if (curB + curC >= C) {
                q.offer(new Node(curA, curB - (C - curC), C));
            } else {
                q.offer(new Node(curA, 0, curB + curC));
            }

            // C -> A
            if (curC + curA >= A) {
                q.offer(new Node(A, curB, curC - (A - curA)));
            } else {
                q.offer(new Node(curA + curC, curB, 0));
            }

            // C -> B
            if (curC + curB >= B) {
                q.offer(new Node(curA, B, curC - (B - curB)));
            } else {
                q.offer(new Node(curA, curB + curC, 0));
            }
        }
    }
}