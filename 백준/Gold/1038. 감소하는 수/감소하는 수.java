import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Long> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        if (N < 10) {
            System.out.println(N);
        } else if (N >= 1023) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < 10; i++) {
                dfs(1, i);
            }
            Collections.sort(list);
            System.out.println(list.get(N));
        }
    }

    static void dfs(int cnt, long num) {
        if (cnt > 10) {
            return;
        }

        list.add(num);
        for (int i = 0; i < num % 10; i++) {
            dfs(cnt + 1, num * 10 + i);
        }
    }
}