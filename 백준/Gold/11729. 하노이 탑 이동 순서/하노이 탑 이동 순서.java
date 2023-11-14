import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int cnt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        hanoi(N, 1, 2, 3);
        System.out.println(cnt);
        System.out.println(sb.toString());
    }

    private static void hanoi(int n, int from, int tmp, int to) {
        if (n == 0) {
            return;
        }

        // n번째 원판 위에 있는 n - 1개의 원판을 임시 기둥으로 이동
        hanoi(n - 1, from, to, tmp);

        // n번 원판 목적지로 이동
        sb.append(from).append(" ").append(to).append("\n");
        cnt++;
        // 이전에 옮겼던 n - 1개의 원판을 원래 목적지였던 기둥으로 이동
        hanoi(n - 1, tmp, from, to);
    }
}