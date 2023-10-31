import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static String S;
    static int Q, L, R;
    static char A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        S = br.readLine();
        // 해당 위치까지 각 알파벳 누적횟수
        // [3][0] >> 3번째 위치까지 0(a)의 누적횟수
        int[][] arr = new int[S.length()][27];
        arr[0][S.charAt(0) - 'a'] = 1;
        for (int i = 1; i < S.length(); i++) {
            for (int j = 0; j < 27; j++) {
                arr[i][j] = arr[i - 1][j];
            }
            arr[i][S.charAt(i) - 'a']++;
        }

        Q = Integer.parseInt(br.readLine());
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            A = st.nextToken().charAt(0);
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            int result = L == 0 ? arr[R][A - 'a'] : arr[R][A - 'a'] - arr[L - 1][A - 'a'];
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}