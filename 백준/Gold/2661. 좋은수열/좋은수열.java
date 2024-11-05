import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        flag = false;

        dfs("");
    }

    static void dfs(String s) {
        if (s.length() == N) {
            flag = true;
            System.out.println(s);
            return;
        }

        for (int i = 1; i <= 3 && !flag; i++) {
            if (isValid(s + i)) {
                dfs(s + i);
            }
        }
    }

    // 이전까지는 이미 좋은 수열이다.
    // 마지막에 넣은 숫자를 기준으로 앞에 숫자들과 비교했을 때 좋은 수열이면 된다.
    private static boolean isValid(String s) {
        int len = s.length();

        // 확인할 길이
        for (int i = 1; i <= len / 2; i++) {
            // 가능한 front 시작점
            String front = s.substring(len - i * 2, len - i);
            String back = s.substring(len - i, len);
            if (front.equals(back)) {
                return false;
            }
        }

        return true;
    }
}