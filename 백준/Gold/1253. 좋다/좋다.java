import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int answer = 0;
        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;
            int target = arr[i];

            // left는 증가시키고 right는 감소시키며
            // left, right, i가 다르고 sum과 target이 같은 경우를 찾는다.
            while (left < right) {
                int sum = arr[left] + arr[right];

                // sum과 target이 같을 경우
                if (sum == target) {
                    // 좋은 수가 되기 위해선 left, right, i는 같지 않아야 한다.
                    if (left != i && right != i) {
                        answer++;
                        break;
                    } else if (left == i) { // i와 같지 않도록 left 증가
                        left++;
                    } else {    // i와 같지 않도록 right 감소
                        right--;
                    }
                } else if (sum < target) {  // 정렬이 되어있기에 sum을 키우기 위해선 left가 증가해야 한다.
                    left++;
                } else {    // 정렬이 되어있기에 sum을 줄이기 위해선 right를 줄여야 한다.  
                    right--;
                }
            }
        }

        System.out.println(answer);
    }
}