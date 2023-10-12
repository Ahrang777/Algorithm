import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/15650
 * N과 M (2)
 * @author SSAFY
 *
 */
public class Main {
	static StringBuilder sb;
	static int N, M, output[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		output = new int[M];
		
		comb(0, 1);
		System.out.println(sb);
	}
	
	private static void comb(int cnt, int start) {
		if (cnt == M) {
			for (int o : output) {
				sb.append(o).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			output[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}
}