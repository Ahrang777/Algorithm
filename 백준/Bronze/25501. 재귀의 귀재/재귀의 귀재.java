import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();

			cnt = 0;
			sb.append(isPalindrome(str)).append(" ").append(cnt).append("\n");
		}

		System.out.println(sb);
	}

	private static int isPalindrome(String str) {
		return recursion(str, 0, str.length() - 1);
	}

	private static int recursion(String str, int start, int end) {
		cnt++;
		if (start >= end)	return 1;	// 기저조건
		else if (str.charAt(start) != str.charAt(end)) {
			return 0;
		} else {
			return recursion(str, start + 1, end - 1);
		}
	}
}