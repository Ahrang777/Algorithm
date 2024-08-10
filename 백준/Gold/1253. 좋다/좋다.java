import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static Map<Integer, Integer> visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int answer = 0;
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        visited = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int key = arr[i] + arr[j];

                if (visited.get(key) != null) {
                    continue;
                }

                int lower = lowerBound(key);
                int upper = upperBound(key);

                if (lower < N && arr[lower] == key) {
                    // 0, 0, 0, 1, 2, 2  >> 0 + 0 = 0 가능, 0 + 1 = 1 불가능 처리
                    if (!isValid(i, j, lower, upper)) {
                        continue;
                    }

//                    set.add(key);
                    answer += (upper - lower);
                    visited.put(key, 1);
                }
            }
        }


//        for (int key : set) {
//            int lower = lowerBound(key);
//            int upper = upperBound(key);
//
//            System.out.println(key);
//            System.out.println("lower : " + lower + " upper : " + upper);
//
//            answer += (upper - lower);
//            System.out.println("answer : " + answer);
//        }

        System.out.println(answer);
    }

    private static boolean isValid(int i, int j, int lower, int upper) {
        if ((i >= lower && i < upper) || (j >= lower && j < upper)) {
            int cnt = 0;
            if (i >= lower && i < upper) {
                cnt++;
            }

            if (j >= lower && j < upper) {
                cnt++;
            }

            return cnt < upper - lower;
        }

        return true;
    }

    private static int lowerBound(int key) {
        int low = 0, high = N;

        while (low < high) {
            int mid = (low + high) / 2;

            if (key <= arr[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private static int upperBound(int key) {
        int low = 0, high = N;

        while (low < high) {
            int mid = (low + high) / 2;

            if (key < arr[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}