import java.io.*;
import java.util.*;

public class Main {
    static int N, len;
    static int[] words; // 알파벳별 개수
    static char[] output;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        words = new int[26];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            len = str.length();
            output = new char[len];

            Arrays.fill(words, 0);

            for (int j = 0; j < len; j++) {
                char ch = str.charAt(j);
                words[ch - 'a']++;
            }

            dfs(0, sb);
        }

        System.out.println(sb);
    }

    private static void dfs(int cnt, StringBuilder sb) {
        if (cnt == len) {
            for (int i = 0; i < len; i++) {
                sb.append(output[i]);
            }
            sb.append("\n");
            return;
        }

        // 알파벳 오름차순으로 확인하기 때문에 굳이 결과에서 정렬할 필요없다.
        // 그냥 결과로 나온 애너그램 순서 그대로 출력하면 된다.
        for (int i = 0; i < 26; i++) {
            if (words[i] <= 0) {
                continue;
            }

            words[i]--;
            output[cnt] = (char) ('a' + i);
            dfs(cnt + 1, sb);
            words[i]++;
        }
    }
}