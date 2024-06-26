import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String S;
    static int[] counts = new int[26];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        S = br.readLine();

        for (int i = 0; i < S.length(); i++) {
            counts[S.charAt(i) - 'a']++;
        }

        for (int count : counts) {
            sb.append(count).append(" ");
        }

        System.out.println(sb);
    }
}