import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, output[];
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		output = new int[M];

		permutation(0);
		System.out.println(sb);
	}

	private static void permutation(int cnt) {
		if (cnt == M) {
			for (int n : output) {
				sb.append(n).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			output[cnt] = i;
			permutation(cnt + 1);
		}
	}
}