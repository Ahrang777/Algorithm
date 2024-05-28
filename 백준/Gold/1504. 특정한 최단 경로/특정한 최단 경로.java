import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int N, E;
	public static void main(String... args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stk.nextToken());
		E = Integer.parseInt(stk.nextToken());
		int[][] arr = new int[N][N];
		for(int i = 0 ;i < E ; i++) {
			stk = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(stk.nextToken());
			int b = Integer.parseInt(stk.nextToken());
			int c = Integer.parseInt(stk.nextToken());
			arr[a-1][b-1] = c;
			arr[b-1][a-1] = c;
		}
		stk = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(stk.nextToken())-1;
		
		int v2 = Integer.parseInt(stk.nextToken())-1;
		
		
		int result = Math.min(
		dijkstra(arr, 0, v1) + dijkstra(arr, v1, v2) + dijkstra(arr, v2, N-1),
		dijkstra(arr, 0, v2) + dijkstra(arr, v2, v1) + dijkstra(arr, v1, N-1)
		);
		if(result >= 10000000) {
			result = -1;
		}
		System.out.println(result);
	}

	private static int dijkstra(int[][] arr, int start, int end) {
		
		int[] distance = new int[N];
		boolean[] visited = new boolean[N];
		
		Arrays.fill(distance, 10000000);
		distance[start] = 0; 
		
		for(int i = 0; i < N ; i++) {
			
			int min = Integer.MAX_VALUE;
			int current = 0; 
			
			for(int j = 0 ; j < N; j++) {
				
				if(!visited[j] && min>distance[j]) {
					
					min = distance[j];
					current = j;
					
				}
				
			}
			
			visited[current] = true; 
			
			if(current == end) break;
			
			
			for(int j = 0; j < N ; j++){

				if(!visited[j] && arr[current][j] != 0 && distance[j] > min + arr[current][j] ) {
					distance[j] = min + arr[current][j];
				}
			}
			
		}
		return distance[end];
		
	}
}