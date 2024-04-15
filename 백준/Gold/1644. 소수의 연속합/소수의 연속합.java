import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        findPrime();

        int s = 0, e = 0, total = 0, cnt = 0;
        int size = list.size();
        while (s <= size && e <= size) {
            if (total == N) {
                cnt++;
            }

            if (e == size) {
                if (total < N) {
                    break;
                }
                total -= list.get(s);
                s++;
                continue;
            }

            if (total <= N) {
                total += list.get(e);
                e++;
            } else {
                total -= list.get(s);
                s++;
            }
        }

        System.out.println(cnt);
    }

    private static void findPrime() {
        // t : 소수 아님, f : 소수
        boolean[] primes = new boolean[N + 1];
        primes[0] = primes[1] = true;

        int sqrt = (int) Math.sqrt(N);
        for (int i = 2; i <= sqrt; i++) {
            if (!primes[i]) {
                for (int j = i; i * j <= N; j++) {
                    primes[i * j] = true;
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!primes[i]) {
                list.add(i);
            }
        }
    }
}