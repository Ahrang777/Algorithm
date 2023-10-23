import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static int K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine());

			// 입력값, 개수
			TreeMap<Integer, Integer> map = new TreeMap<>();

			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				char command = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());

				if (command == 'I') {
					map.put(num, map.getOrDefault(num, 0) + 1);
				} else {
					if (map.isEmpty())	continue;
					if (num == -1) {	// 최솟값 삭제
						num = map.firstKey();
					} else {	// 최댓값 삭제
						num = map.lastKey();
					}

					map.put(num, map.get(num) - 1);
					if (map.get(num) == 0) {
						map.remove(num);
					}
				}
			}

			if (map.size() == 0) {
				sb.append("EMPTY");
			} else {
				sb.append(map.lastKey()).append(" ").append(map.firstKey());
			}
			sb.append("\n");
		}
		System.out.println(sb);


	}
}