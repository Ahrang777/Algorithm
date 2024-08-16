import java.io.*;
import java.util.*;

public class Main {
    static int N, M, L;
    static int[] arr, subArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N + 2];

        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
        }

        arr[0] = 0;
        arr[N + 1] = L;

        Arrays.sort(arr);

        int s = 1;
        int e = L - 1;
        int answer = L - 1;

        while (s <= e) {
            // 현재 구간에서 휴게소가 없는 구간의 최댓값
            int mid = (s + e) / 2;

            // 휴게소 없는 구간의 최댓값이 mid일 경우 추가 설치 가능한 휴게소의 수
            int cnt = 0;

            for (int i = 1; i < arr.length; i++) {
                // arr[i] - arr[i - 1] - 1로 마지막에 -1을 해줘야 한다.
                // 길이 6, mid 2라고 가정하면 6/2 = 3이지만 실제로 설치 가능한 휴게소는 2개이다.
                // 길이 7, mid 2인 경우 7/2 = 3이고 설치 가능한 휴게소는 3개이다.
                // 이처럼 -1을 해줘서 mid로 딱 나눠떨어지는 경우를 처리해준다.
                // 아니면 아래처럼 나누어떨어질때 처리를 해줘도 된다.
                // cnt += (arr[i] - arr[i - 1]) / mid; 
                // if ((arr[i] - arr[i - 1]) % mid) == 0) cnt--; 
                cnt += (arr[i] - arr[i - 1] - 1) / mid;
            }
     
            if (cnt > M) { // 휴게소 없는 구간의 최댓값이 mid가 되기위해서는 M개보다 많은 휴게소가 필요하면 답이 될 수 없다.
                s = mid + 1;
            } else {
                // M개 이하로 세우는 경우여야 가능하다.
                // M개로 딱 되는거 ok, M개보다 적게 되도 일부를 mid보다 더 적게 나누면 M개 맞출수 있어서 ok
                
                // 현재 M값과 동일하게 휴게소를 세우거나 휴게소가 더 적게 세워지는 건 현재 mid가 휴게소 없는 구간의 최댓값이라는 의미
                // cnt < M인 경우도 괜찮은 이유는
                // 현재는 최댓값인 mid와 동일하게 나눈다고 가정하여 M보다 작은거지
                // 하나만 mid로 나누고 나머지는 더 작은 값으로 나우어 휴게소를 M개 세울 수 있다.
                // 구간 길이 : 1, 1, 1, mid 이런식으로 (mid > 1) 
                e = mid - 1;
                answer = mid;
            }
        }

        System.out.println(answer);
    }


}