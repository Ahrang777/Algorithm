import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int result = combination(N, K);

        System.out.println(result);
    }

    private static int combination(int n, int r) {
        if (n == r || r == 0) {
            return 1;
        }

        return combination(n - 1, r - 1) + combination(n - 1, r);
    }
}
