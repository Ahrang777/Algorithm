import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static char[] arr, output;
    static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        output = new char[L];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        dfs(0, 0, 0, 0);
        System.out.println(sb);
    }

    private static void dfs(int cnt, int start, int consonantsCnt, int vowelsCnt) {
        if (cnt == L) {
            if (consonantsCnt < 2 || vowelsCnt < 1) {
                return;
            }

            for (char c : output) {
                sb.append(c);
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < C; i++) {
            output[cnt] = arr[i];

            if (isVowel(arr[i])) {
                dfs(cnt + 1, i + 1, consonantsCnt, vowelsCnt + 1);
            } else {
                dfs(cnt + 1, i + 1, consonantsCnt + 1, vowelsCnt);
            }
        }
    }

    private static boolean isVowel(char c) {
        for (char vowel : vowels) {
            if (c == vowel) {
                return true;
            }
        }

        return false;
    }
}