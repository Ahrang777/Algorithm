import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int cnt = 0;

            Map<String, Integer> map = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                map.put(st.nextToken(), 1);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                String input = st.nextToken();
                if (map.containsKey(input)) {
                    cnt++;
                }
            }

            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }

        System.out.println(sb.toString());
    }
}