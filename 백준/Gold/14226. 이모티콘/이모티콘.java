import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static class Node {
        int screen, clip, time;

        public Node(int screen, int clip, int time) {
            this.screen = screen;
            this.clip = clip;
            this.time = time;
        }
    }
    static int S, result;
    static int[] arr = new int[1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());

        bfs(1, 0);
        System.out.println(result);

    }

    private static void bfs(int screen, int clip) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[1001][1001];
        q.offer(new Node(screen, clip, 0));
        visited[screen][clip] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.screen == S) {
                result = now.time;
                break;
            }

            // 복사 >> 암거나
            if (!visited[now.screen][now.screen]) {
                q.offer(new Node(now.screen, now.screen, now.time + 1));
                visited[now.screen][now.screen] = true;
            }
            
            // 붙여넣기 >> 방문 안한
            if (now.clip != 0 && now.screen + now.clip <= S && !visited[now.screen + now.clip][now.clip]) {
                visited[now.screen + now.clip][now.clip] = true;
                q.offer(new Node(now.screen + now.clip, now.clip, now.time + 1));
            }
            // 삭제 >> 화면 != 0
            if (now.screen >= 1 && !visited[now.screen - 1][now.clip]) {
                visited[now.screen - 1][now.clip] = true;
                q.offer(new Node(now.screen - 1, now.clip, now.time + 1));
            }
        }

    }
}