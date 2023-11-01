import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. N(수의 개수), M(나누기 할 수) 입력받기
        int N = Integer.parseInt(st.nextToken());   // 수의 개수
        int M = Integer.parseInt(st.nextToken());   // 나누기 할 수
        long result = 0;                            // M으로 나누어떨어지는 (i,j) 쌍의 개수
        long[] S = new long[N + 1];                 // 합배열
        long[] cnt = new long[M];                   // 같은 나머지의 인덱스를 카운트하는 배열

        // 2. N개의 수 입력받으면서 누적합을 M으로 나눈 나머지를 배열 S에 저장한다.
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            S[i] = (S[i - 1] + Integer.parseInt(st.nextToken())) % M;
            // 0~i까지의 합을 M으로 나눈 나머지가 0인 경우의 수 카운팅
            if(S[i] == 0) {
                result++;
            }
            // 나머지가 같은 인덱스의 수 카운팅
            cnt[(int) S[i]]++;
        }

        // 3. S[j] % M == S[i-1] % M 을 만족하는 (i,j)의 수를 결과값에 더한다.
        // 즉, cnt[i](i가 나머지인 인덱스의 수)에서 2가지를 뽑는 경우의 수 카운팅한다.
        for(int i=0; i<M; i++) {
            if(cnt[i] > 1) {
                result += (cnt[i]* (cnt[i]-1) / 2);
            }
        }
        System.out.println(result);
    }

    // 시간초과
    /*static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        int[] calc = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int len = N;

            while (len >= i) {
                calc[len] = (calc[len - 1] + arr[len - 1] % M) % M;
                if (calc[len] == 0) {
                    result++;
                }
                len--;
            }
        }

        System.out.println(result);
    }*/

    // 아래 풀이는 메모리 초과가 나온다 >> 2차원 배열이 아닌 1차원 배열로 해결해야 한다.
    /*public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

//      mod연산 분배법칙 활용 (A + B) % P = ((A % P) + (B % P)) % P
//      행: 연속된 숫자의 수, 열: 배열 arr의 idx로 해당 idx를 반영
//      행: i, 열: j일 경우 calc[i][j] = (calc[i - 1][j - 1] + arr[j - 1] % M) % M
//      calc[i - 1][j - 1]는 i - 1개 연속된 수, j - 1까지 반영하여 나온 mod 결과값
//      calc[i - 1][j - 1]는 이전까지 mod연산한 결과이므로 추가될 arr[j - 1]만 % M 처리하고 더한 결과에 다시 % M 처리하여 저장
//      저장된 값이 == 0 인경우 idx(열j) 까지 포함된 연속된 개수(행i) 숫자합이 M으로 나누어떨어지는 경우이다.
//      (A + B + C) % P = (((A + B) % P) + (C % P)) % P = (((A % P) + (B % P)) % P + (C % P)) % P
//      여기서 ((A % P) + (B % P)) % P 가 calc[i - 1][j - 1] 에 해당
//      (C % P) 가 arr[j - 1] % M 에 해당
//      위 연산을 간단히 표현하면 ((A % P) + (B % P)) % P 를 A' (C % P)를 B' 이라고 했을 때 (A' + B') % P
//      따라서 (A' + B') % P 가 (calc[i - 1][j - 1] + arr[j - 1] % M) % M
        int[][] calc = new int[N + 1][N + 1];
        int result = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                calc[i][j] = (calc[i - 1][j - 1] + arr[j - 1] % M) % M;
                if (calc[i][j] == 0)    result++;
            }
        }

        System.out.println(result);
    }*/
}