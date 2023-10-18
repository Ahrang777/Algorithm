import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[] isBroken;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
				
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		isBroken = new boolean[10];
		
		if (M != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int idx = Integer.parseInt(st.nextToken());
				isBroken[idx] = true;
			}			
		}
		
		
		int min = Math.abs(N - 100);
		for (int i = 0; i < 1000000; i++) {
			String str = String.valueOf(i);
			int len = str.length();
			boolean flag = true;
			
			for (int j = 0; j < len; j++) {
				if (isBroken[str.charAt(j) - '0']) {
					flag = false;
					break;
				}
			}
			
			if (flag) {
				min = Math.min(min, Math.abs(N - i) + len);
			}
		}
		
		
		System.out.println(min);
	}
}