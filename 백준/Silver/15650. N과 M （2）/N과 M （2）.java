import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, intput[], output[];
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
//		intput = new int[N];
		sb = new StringBuilder();
		output = new int[M];
		
		
				
		comb(0, 0);
		System.out.println(sb);
	}
	
	private static void comb(int cnt, int start) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
//				System.out.print(output[i] + " ");
				sb.append(output[i]).append(" ");
			}
//			System.out.println();
			sb.append("\n");
			return;
		}
		
		for (int i = start; i < N; i++) {
//			output[cnt] = intput[i];
			output[cnt] = i + 1;
			comb(cnt + 1, i + 1);
		}
	}
}