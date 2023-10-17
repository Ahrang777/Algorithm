import java.io.*;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/11659
 * 구간 합 구하기 4
 * @author SSAFY
 *
 */
public class Main {
	static int N, M, arr[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());		
		for (int i = 1; i <= N; i++) {
			arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());						
			
			sb.append(arr[e] - arr[s - 1]).append("\n");
		}
		
		System.out.println(sb);
	}
}