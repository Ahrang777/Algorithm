import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int l, h;

        public Node(int l, int h) {
            this.l = l;
            this.h = h;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.l, o.l);
        }
    }
    static int N;
    static List<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            list.add(new Node(l, h));
        }

        Collections.sort(list);

        int maxIndex = 0;
        for (int i = 0; i < N; i++) {
            if (list.get(i).h > list.get(maxIndex).h) {
                maxIndex = i;
            }
        }

        // 최고높이
        int total = list.get(maxIndex).h;

        // 왼쪽 -> 최고높이
        int leftMax = list.get(0).h;

        for (int i = 0; i < maxIndex; i++) {
            leftMax = Math.max(leftMax, list.get(i).h);
            int width = list.get(i + 1).l - list.get(i).l;
            total += leftMax * width;
        }

        // 오른쪽 -> 최고높이
        int rightMax = list.get(N - 1).h;
        for (int i = N - 1; i > maxIndex; i--) {
            rightMax = Math.max(rightMax, list.get(i).h);
            int width = list.get(i).l - list.get(i - 1).l;
            total += rightMax * width;
        }

        System.out.println(total);
    }
}
