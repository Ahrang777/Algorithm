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
		long[] factorial = new long[1000001];
		factorial[0] = 1;
		for (int i = 1; i <= 1000000; i++) {
			factorial[i] = factorial[i - 1] * i % MOD;
		}
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());

			long bottom = factorial[R] * factorial[N - R] % MOD;
			bottom = fermat(bottom, MOD - 2);

			sb.append("#").append(tc).append(" ").append((factorial[N] * bottom) % MOD).append("\n");
		}
		System.out.println(sb);
	}

	private static long fermat(long a, int p) {
		if (p == 0) {
			return 1;
		}

		long half = fermat(a, p / 2);

		if (p % 2 == 0) {
			return ((half % MOD) * (half % MOD)) % MOD;
		} else {
			return ((half * a) % MOD * (half % MOD)) % MOD;
		}
	}
}