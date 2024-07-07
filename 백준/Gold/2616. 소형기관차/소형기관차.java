import java.io.*;
import java.util.*;

public class Main {
    static int N, M, prefixSum[];
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        prefixSum = new int[N + 1];

        // 행: 소형기관차 번호, 열: 열번호 개의 객차를 선택
        // 소형기관차 i개로 j개의 객차를 끌고갈 때 최대로 운송할 수 있는 손님 수
        dp = new int[4][N + 1];  

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        // j는 i * M 부터 시작
        // 최대의 손님을 태우려면 소형 기관차는 최대의 객차를 끌고 가야하기 때문에 최소 i * M 개는 끌고간다.
        for (int i = 1; i <= 3; i++) {  // i번째 소형기관차
            for (int j = i * M; j <= N; j++) {  // j개의 객차를 선택할 때
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - M] + prefixSum[j] - prefixSum[j - M]);
            }
        }

        System.out.println(dp[3][N]);
    }
}