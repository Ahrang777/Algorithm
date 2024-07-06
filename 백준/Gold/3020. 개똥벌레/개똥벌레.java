import java.util.*;
import java.io.*;

public class Main {
    static int N, H, imos[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        imos = new int[H];

        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {   //  석순 >> 위에서 아래로
                imos[0]++;
                imos[h]--;
            } else {    // 종유석 >> 아래에서 위로
                imos[H - h]++;
            }
        }

        int[] high = new int[H];
        high[0] = imos[0];
        int min = high[0];

        for (int i = 1; i < H; i++) {
            high[i] = high[i - 1] + imos[i];
            if (min > high[i]) {
                min = high[i];
            }
        }

        int cnt = 0;
        for (int i = 0; i < H; i++) {
            if (min == high[i]) {
                cnt++;
            }
        }

        sb.append(min).append(" ").append(cnt);
        System.out.println(sb);
    }
}