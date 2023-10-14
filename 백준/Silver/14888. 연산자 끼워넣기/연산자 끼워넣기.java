import java.util.*;
import java.io.*;

public class Main{
    public static int n;
    public static int[] arr = new int[11];

    public static int min = (int) 1e9;
    public static int max = (int) -1e9;

    public static int add, sub, mul, div;

    public static void calculation(int i, int now) {
        if (i == n) {
            min = Math.min(min, now);
            max = Math.max(max, now);
        } else {
            if (add > 0) {
                add--;
                calculation(i + 1, now + arr[i]);
                add++;
            }
            if (sub > 0) {
                sub--;
                calculation(i + 1, now - arr[i]);
                sub++;
            }
            if (mul > 0) {
                mul--;
                calculation(i + 1, now * arr[i]);
                mul++;
            }
            if (div > 0) {
                div--;
                calculation(i + 1, now / arr[i]);
                div++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = null;

        n = Integer.parseInt(bf.readLine());

        stk = new StringTokenizer(bf.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        stk = new StringTokenizer(bf.readLine(), " ");
        add = Integer.parseInt(stk.nextToken());
        sub = Integer.parseInt(stk.nextToken());
        mul = Integer.parseInt(stk.nextToken());
        div = Integer.parseInt(stk.nextToken());

        calculation(1, arr[0]);

        System.out.println(max);
        System.out.println(min);
    }
}