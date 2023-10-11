import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, R;
	static final int MOD = 1234567891;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());

			long[] fact = new long[N + 1];
			fact[0] = 1;
			for (int i = 1; i <= N; i++) {
				fact[i] = (fact[i - 1] * i) % MOD;
			}

			long bottom = (fact[R] * fact[N - R]) % MOD;
			bottom = fermat(bottom, MOD - 2);

			sb.append("#").append(tc).append(" ").append((fact[N] * bottom) % MOD).append("\n");
		}
		System.out.println(sb);
	}

	private static long fermat(long n, int x) {
		if (x == 0) return 1;

		long tmp = fermat(n, x / 2);
		long ret = (tmp * tmp) % MOD;
		if (x % 2 == 0) return ret;
		else return (ret * n) % MOD;
	}
}