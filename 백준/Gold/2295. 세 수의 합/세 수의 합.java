import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr, twoSum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        twoSum = new int[N * (N + 1) / 2];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int index = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                twoSum[index++] = arr[i] + arr[j];
            }
        }

        Arrays.sort(twoSum);
        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int s = 0;
                int e = twoSum.length - 1;
                int target = arr[i] - arr[j];

                while (s <= e) {
                    int mid = (s + e) / 2;

                    if (target == twoSum[mid]) {
                        answer = Math.max(twoSum[mid] + arr[j], answer);
                        break;
                    } else if (target > twoSum[mid]) {
                        s = mid + 1;
                    } else {
                        e = mid - 1;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}