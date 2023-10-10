import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			String commands = br.readLine();
			N = Integer.parseInt(br.readLine());
			String[] arr = new String[N];
			
			String numbers = br.readLine();
			boolean isReverse = false;	// 뒤집힘 여부
			boolean isError = false;	// 에러 발생 여부
			
			// 투포인터
			int start = 0, end = N - 1;
			
			if (N == 0) {
				// D 포함한 경우 에러 
				if (commands.contains("D")) {
					isError = true;
				}				
			} else {				
				int idx = 0;
				
				numbers = numbers.substring(1, numbers.length() - 1);
				arr = numbers.split(",");
//				System.out.println(Arrays.toString(arr));
				
				while (start <= end) {
					if (idx >= commands.length())	break;
					char command = commands.charAt(idx++);
					
					if (command == 'R') {	// 뒤집기
						isReverse = !isReverse;
					} else {	// 제거
						if (isReverse) {	// 뒤에서 제거 
							end--;
						} else {	// 앞에서 제거 
							start++;
						}
					}
				}
				
				// 아직 남은 command가 있는 경우
				if (idx < commands.length()) {	
					commands = commands.substring(idx);
					
					// 배열은 비었는데 D 연산이 있는 경우 
					if (commands.contains("D")) {
						isError = true;
					}
				}
			}
			
						
			// error 인 경우
			if (isError) {
				sb.append("error").append("\n");
				continue;
			}
			
			sb.append("[");

			// 배열 크기 0이 아닌 경우 
			if (N != 0 && start <= end) {
				// 뒤집힌 경우 
				if (isReverse) {
					for (int i = end; i >= start; i--) {					
						sb.append(arr[i]).append(",");					
					}
				} else { // 기존 순서와 동일
					for (int i = start; i <= end; i++) {
						sb.append(arr[i]).append(",");
					}
				}
				sb.deleteCharAt(sb.length() - 1);	// 마지막 , 제거				
			}
			
			sb.append("]").append("\n");
			
		}
		
		System.out.println(sb);		
	}
}