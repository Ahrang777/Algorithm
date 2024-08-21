import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        Set<String> set = new HashSet<>();
        Set<String> tmp = new HashSet<>();
        for (int j = 0; j < C; j++) {
            String str = "";
            for (int i = 0; i < R; i++) {
                str += arr[i][j];
            }

            set.add(str);
        }

        int answer = 0;
        for (int i = 0; i < R - 1; i++) {
            for (String s : set) {
                tmp.add(s.substring(1));
            }

            set.clear();
            set.addAll(tmp);
            tmp.clear();

            if (set.size() != C) {
                break;
            }

            answer++;
        }

        System.out.println(answer);
    }
}