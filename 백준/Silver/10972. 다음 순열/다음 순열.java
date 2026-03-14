import java.io.*;
import java.util.*;

public class Main {
    static int N, arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());


        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (nextPermutation()) {
            for (int num : arr) {
                sb.append(num).append(" ");
            }
        } else {
            sb.append("-1");
        }

        System.out.println(sb);
    }

    private static boolean nextPermutation() {
        int i = N - 1;

        //1. A[i-1] < A[i] 를 만족하는 가장 큰 i를 찾는다.
        while(i > 0 && arr[i-1] >= arr[i]) {
            i -= 1;
        }

        //i의 위치가 0이면 내림차순(마지막 순열)
        if(i<=0) return false;

        int j = N - 1;

        //2. j >= i 이면서 A[j] > A[i-1] 을 만족하는 가장 큰 j를 찾는다.
        while(arr[i-1] >= arr[j]) {
            j -= 1;
        }

        //3. A[i-1]과 A[j] 를 swap 한다.
        int temp = arr[j];
        arr[j] = arr[i-1];
        arr[i-1] = temp;

        j = N - 1;

        //4. A[i] 부터 순열을 뒤집는다.
        while(i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i += 1;
            j -= 1;
        }

        return true;
    }
}
