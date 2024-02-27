import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/3273
 * 두 수의 합
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int X = Integer.parseInt(br.readLine());

		int s = 0, e = N - 1, cnt = 0;
		while (s < e) {
			int sum = arr[s] + arr[e];
			if (sum == X) {
				cnt++;
				s++;
				e--;
			} else if (sum > X) {
				e--;
			} else {
				s++;
			}
		}

		System.out.println(cnt);
	}
}