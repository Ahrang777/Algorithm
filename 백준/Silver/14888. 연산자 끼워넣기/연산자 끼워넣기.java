import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int n;
	public static int[] numbers = new int[11];

	public static int min = (int) 1e9;
	public static int max = (int) -1e9;

	public static int add, sub, mul, div;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		n = Integer.parseInt(bf.readLine());

		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(bf.readLine());
		add = Integer.parseInt(st.nextToken());
		sub = Integer.parseInt(st.nextToken());
		mul = Integer.parseInt(st.nextToken());
		div = Integer.parseInt(st.nextToken());

		calculation(1, numbers[0]);

		System.out.println(max);
		System.out.println(min);
	}

	public static void calculation(int cnt, int result) {
		if (cnt == n) {
			min = Math.min(min, result);
			max = Math.max(max, result);
			return;
		}

		if (add > 0) {
			add--;
			calculation(cnt + 1, result + numbers[cnt]);
			add++;
		}
		if (sub > 0) {
			sub--;
			calculation(cnt + 1, result - numbers[cnt]);
			sub++;
		}
		if (mul > 0) {
			mul--;
			calculation(cnt + 1, result * numbers[cnt]);
			mul++;
		}
		if (div > 0) {
			div--;
			calculation(cnt + 1, result / numbers[cnt]);
			div++;
		}
	}
}