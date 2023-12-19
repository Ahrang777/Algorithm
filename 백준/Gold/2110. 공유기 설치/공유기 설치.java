import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[] homes = new int[N];
		for (int i = 0; i < N; i++) {
			homes[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(homes);

		int start = 1;
		int end = homes[N - 1] - homes[0];
		int result = 0;

		while (start <= end) {
			int mid = (start + end) / 2;	// 가장 인접한 공유기 사이 거리

			int prev = 0;
			int cnt = 1;

			for (int i = 1; i < N; i++) {
				if (homes[i] - homes[prev] >= mid) {
					cnt++;
					prev = i;
				}
			}

			if (cnt >= C) {
				start = mid + 1;
				result = Math.max(result, mid);
			} else {
				end = mid - 1;
			}
		}

		System.out.println(result);
	}
}