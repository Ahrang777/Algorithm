import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
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
        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            // 해당하지 않는 index 값들인 경우 제거 >> 앞이 가장 오래되었기에 앞에서부터 확인
            // 범위내의 값만 남도록 정리
            while (!dq.isEmpty() && dq.peekFirst()[0] < i - L + 1) {
                dq.pollFirst();
            }
            
            // 맨뒤에 추가해 나가기에 맨뒤가 최신
            // 현재 있는 값들은 모두 범위내에 있는 값들이기에 맨 마지막의 값은 현재 넣을 n보다 먼저 범위 밖으로 나간다.
            // 따라서 n이 맨 마지막(가장 최근) value보다 큰 값이될때까지 dq 뒤에서부터 제거 
            // 즉, 현재 있는 값들은 범위 내에 있는 값들이라 n보다 큰 값이면 범위내 최솟값이 될 수 없으므로 n보다 큰 것들 제거
            while (!dq.isEmpty() && dq.peekLast()[1] > n) {
                dq.pollLast();
            }

            dq.offerLast(new int[]{i, n});
            sb.append(dq.peek()[1]).append(" ");
        }

        System.out.println(sb.toString());
    }
}