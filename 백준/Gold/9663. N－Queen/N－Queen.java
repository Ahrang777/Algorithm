import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, result;
    static int[] output;    // 해당 행(idx)의 열(요소)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        output = new int[N];

        solve(0);
        System.out.println(result);
    }

    private static void solve(int row) {
        if (row == N) {
            result++;
            return;
        }

        // col >> i
        for (int i = 0; i < N; i++) {
            output[row] = i;
            if (!isValid(row)) {
                continue;
            }
            solve(row + 1);
        }
    }

    // 행의 경우 row를 1씩 증가하면서 처리했기에 output 의 idx를 1씩 증가하면서 처리했기에 ok
    private static boolean isValid(int row) {
        for (int i = 0; i < row; i++) {
            // col 체크
            if (output[i] == output[row])	return false;

            // 대각선 체크 >> row의 차이 절대값, col 차이 절대값 동일하면 대각선 >> ex) row: -2, col: +2
            if (Math.abs(i - row) == Math.abs(output[i] - output[row]))	return false;
        }

        return true;
    }
}