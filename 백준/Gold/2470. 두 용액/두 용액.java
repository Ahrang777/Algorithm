import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int start = 0;
		int end = n - 1;
		int min = Integer.MAX_VALUE;
		int n1 = 0, n2 = 0;

		while (start < end) {
			int sum = arr[start] + arr[end];

			if (Math.abs(sum) < min) {
				n1 = arr[start];
				n2 = arr[end];
				min = Math.abs(sum);
			}

			if (sum > 0) {
				end--;
			} else {
				start++;
			}
		}

		System.out.println(n1 + " " + n2);
	}
}