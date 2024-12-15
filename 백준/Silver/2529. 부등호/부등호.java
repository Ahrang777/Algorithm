import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String[] arr;
    static boolean[] isSelected;
    static int[] output;
    static long max = Long.MIN_VALUE, min = Long.MAX_VALUE;
    static String maxResult, minResult;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        isSelected = new boolean[10];
        output = new int[N + 1];

        arr = br.readLine().split(" ");

        for (int i = 0; i < 10; i++) {
            isSelected[i] = true;
            output[0] = i;
            dfs(1);
            isSelected[i] = false;
        }

        System.out.println(maxResult);
        System.out.println(minResult);
    }

    private static void dfs(int cnt) {
        if (cnt == N + 1) {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N + 1; i++) {
                sb.append(output[i]);
            }

            long num = Long.parseLong(sb.toString());

            if (max < num) {
                max = num;
                maxResult = sb.toString();
            }

            if (min > num) {
                min = num;
                minResult = sb.toString();
            }

            return;
        }

        for (int i = 0; i < 10; i++) {
            if (isSelected[i]) {
                continue;
            }

            if (arr[cnt - 1].equals(">")) {
                if (output[cnt - 1] > i) {
                    isSelected[i] = true;
                    output[cnt] = i;
                    dfs(cnt + 1);
                    isSelected[i] = false;
                }
            } else {
                if (output[cnt - 1] < i) {
                    isSelected[i] = true;
                    output[cnt] = i;
                    dfs(cnt + 1);
                    isSelected[i] = false;
                }
            }
        }
    }
}