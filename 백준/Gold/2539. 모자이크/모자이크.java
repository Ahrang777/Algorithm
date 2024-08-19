import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.y, o.y);
        }
    }
    static int N, M, cnt, error;
    static List<Node> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        // 사용할 색종이 장 수
        cnt = Integer.parseInt(br.readLine());

        // 잘못 칠해진 칸 수
        error = Integer.parseInt(br.readLine());

        int max = 0;
        for (int e = 0; e < error; e++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            max = Math.max(max, x);

            list.add(new Node(x, y));
        }

        Collections.sort(list);

        int s = 0;
        int e = Math.max(N, M);
        int answer = e;

        while (s <= e) {
            // 색종이 크기
            int mid = (s + e) / 2;

            // 크기가 mid인 색종이 cnt 장을 이용하여 모든 잘못 칠해진 칸을 가릴 수 있는 경우
            // mid 색종이로 막는데 cnt 이하 필요
            if (isValid(mid, max)) {
                e = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                s = mid + 1;
            }
        }

        System.out.println(answer);
    }

    // size 크기를 갖는 색종이 cnt 장 이하를 이용하여 잘못 칠해진 모든 칸을 가릴 수 있는지 여부
    private static boolean isValid(int size, int base) {
        // size가 적어도 base 이상 >> 밑변에 붙여서 가리니까 적어도 색종이 크기가 에러 위치의 최대 높이보다 크거나 같아야 한다.
        if (size < base) {
            return false;
        }

        int total = 1;
        int prev = list.get(0).y;
        for (int i = 1; i < list.size(); i++) {
            Node now = list.get(i);

            if (prev + size <= now.y) {
                total++;
                prev = now.y;
                if (total > cnt) {
                    return false;
                }
            }
        }

        return true;
    }
}