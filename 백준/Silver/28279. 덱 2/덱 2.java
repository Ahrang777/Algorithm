import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/28279
 * @author SSAFY
 *
 */
public class Main {	
	static int N;
	static int[] arr = new int[1000000];
	static int front, rear, size;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= N; tc++) {
			st = new StringTokenizer(br.readLine());

			int command = Integer.parseInt(st.nextToken());
			int len = arr.length;

			if (command == 1) {
				int X = Integer.parseInt(st.nextToken());
				arr[front] = X;
				front = (front + len - 1) % len;
				size++;
			} else if (command == 2) {
				int X = Integer.parseInt(st.nextToken());
				rear = (rear + 1) % len;
				arr[rear] = X;				
				size++;
			} else if (command == 3) {
				if (arr[(front + 1) % len] != 0) {
					front = (front + 1) % len;
					sb.append(arr[front]);
					arr[front] = 0;					
					size--;
				} else {
					sb.append(-1);
				}

				sb.append("\n");
			} else if (command == 4) {
				if (arr[rear] != 0) {
					sb.append(arr[rear]);
					arr[rear] = 0;
					rear = (rear + len - 1) % len;
					size--;
				} else {
					sb.append(-1);
				}

				sb.append("\n");
			} else if (command == 5) {
				sb.append(size).append("\n");
			} else if (command == 6) {
				sb.append(size == 0 ? 1 : 0).append("\n");
			} else if (command == 7) {
				if (size != 0) {
					sb.append(arr[(front + 1) % len]);
				} else {
					sb.append(-1);
				}
				sb.append("\n");
			} else if (command == 8) {
				if (size != 0) {
					sb.append(arr[rear]);
				} else {
					sb.append(-1);
				}
				sb.append("\n");
			}
		}

		System.out.println(sb);
	}
}