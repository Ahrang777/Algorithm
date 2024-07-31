import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] map;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static class Node {
        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static boolean outRange(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }

    static int getChangeCnt() {
        Deque<Node> q = new ArrayDeque<>();
        q.offer(new Node(0, 0));

        int[][] changeCount = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(changeCount[i], -1);
        }
        changeCount[0][0] = 0;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.r == n - 1 && curr.c == n - 1) {
                return changeCount[n - 1][n - 1];
            }

            for (int dir = 0; dir < 4; dir++) {
                int nextR = curr.r + dr[dir];
                int nextC = curr.c + dc[dir];
                if (outRange(nextR, nextC)) continue;
                if (changeCount[nextR][nextC] != -1) continue;

                if (map[nextR][nextC] == '1') {
                    q.addFirst(new Node(nextR, nextC));
                    changeCount[nextR][nextC] = changeCount[curr.r][curr.c];
                } else {
                    q.addLast(new Node(nextR, nextC));
                    changeCount[nextR][nextC] = changeCount[curr.r][curr.c] + 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new char[n][];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(getChangeCnt());
    }
}