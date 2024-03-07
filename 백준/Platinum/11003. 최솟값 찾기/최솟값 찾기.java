import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int index, value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    static int N, L;
    static int[] arr, D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        D = new int[N];
        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        // index, value
        // 덱에는 항상 현재까지 범위에 해당하는 최소의 값들로만 유지, 값은 같지만 index가 다른 경우들이 있다. 모두 저장
        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            // 맨뒤에 추가해 나가기에 맨뒤가 최신
            // 가장 작은 값만 남도록 처리
            while (!dq.isEmpty() && dq.peekLast()[1] > n) {
                dq.pollLast();
            }

            // 해당하지 않는 index 값들인 경우 제거 >> 앞이 가장 오래되었기에 앞에서부터 확인
            while (!dq.isEmpty() && dq.peekFirst()[0] < i - L + 1) {
                dq.pollFirst();
            }

            dq.offerLast(new int[]{i, n});
            sb.append(dq.peek()[1]).append(" ");
        }

        System.out.println(sb.toString());
    }
}