import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int index, num;

		public Node(int index, int num) {
			super();
			this.index = index;
			this.num = num;
		}
	}
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder ();
		
		N = Integer.parseInt(br.readLine());
		Deque<Node> dq = new ArrayDeque<>();
		
		st = new StringTokenizer(br.readLine());				
				
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			dq.offerLast(new Node(i, num));
		}				
		
		Node node = dq.pollFirst();
		int index = node.index;
		int num = node.num;
		sb.append(index).append(" ");
		
		while (!dq.isEmpty()) {
			if (num < 0) {	// 뒤에서 poll
				num = -num;
				for (int i = 0; i < num - 1; i++) {
					node = dq.pollLast();
					dq.offerFirst(node);
				}
				node = dq.pollLast();
			} else if (num > 0) {	// 앞에서 poll 
				for (int i = 0; i < num - 1; i++) {
					node = dq.pollFirst();
					dq.offerLast(node);
				}
				node = dq.pollFirst();
			}
			
			index = node.index;
			num = node.num;
			sb.append(index).append(" ");
		}
		System.out.println(sb);
	}
}