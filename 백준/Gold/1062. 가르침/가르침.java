import java.io.*;
import java.util.*;

public class Main {
    static int N, K, max = Integer.MIN_VALUE;
    static String[] words;
    static char[] arr;
    static boolean[] visited;
    static final String BASE = "antic";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        if (K < 5) {    // 필수 글자를 모두 배우지 못한 경우
            max = 0;
        } else if (K == 26) {   // 모든 알파벳 소문자를 배운 경우
            max = N;
        } else {
            // 단어에서 필수 글자를 제외한 알파벳
            visited = new boolean[26];

            for (int i = 0; i < BASE.length(); i++) {
                visited[BASE.charAt(i) - 'a'] = true;
            }

            for (int i = 0; i < N; i++) {
                words[i] = words[i].substring(0, words[i].length() - 4);
                words[i] = words[i].substring(4);
            }

            // set의 크기 n
            // r = K - 5
            // nCr
            // 필수 글자 + 뽑은 알파벳 조합을 포함해서 words에 담긴 단어들을 몇개 만들 수 있는가
            combination(0, 0);
        }

        System.out.println(max);
    }

    private static void combination(int depth, int start) {
        if (depth == K - 5) {
            int total = 0;

            for (String word : words) {
                boolean flag = true;
                for (int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    if (!visited[ch - 'a']) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    total++;
                }
            }
            // 최대값 갱신
            max = Math.max(max, total);
            return;
        }

        for (int i = start; i < 26; i++) {
            if (visited[i]){
                continue;
            }

            visited[i] = true;
            combination(depth + 1, i + 1);
            visited[i] = false;
        }
    }
}