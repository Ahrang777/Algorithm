import java.io.*;
import java.util.*;

public class Main {
    static double X, Y, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Double.parseDouble(st.nextToken());
        Y = Double.parseDouble(st.nextToken());
        C = Double.parseDouble(st.nextToken());

        double s = 0;
        double e = Math.min(X, Y);  // 밑변, 높이 < 대각선

        while (e - s >= 0.001) {
            double mid = (s + e) / 2;
            double h1 = Math.sqrt(Math.pow(X, 2) - Math.pow(mid, 2));
            double h2 = Math.sqrt(Math.pow(Y, 2) - Math.pow(mid, 2));
            double result = (h1 * h2) / (h1 + h2);

            // result 와 C가 같도록 맞춰야 한다.
            // result가 실제 C보다 높게 나왔다면 가로폭을 더 늘려서 result가 작아지도록 해야한다.
            if (result >= C) {
                s = mid;
            } else {
                e = mid;
            }
        }

        System.out.println(String.format("%.3f", e));
    }

    private static boolean isValid(double len) {
        double result = len - C * len / Math.sqrt(Math.pow(X, 2) - Math.pow(len, 2));
        return result == C * len / Math.sqrt(Math.pow(Y, 2) - Math.pow(len, 2));
    }
}