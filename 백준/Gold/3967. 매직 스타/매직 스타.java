import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int[] arr;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1 ~ 12 : A ~ L
        visited = new boolean[13];
        arr = new int[13];
        int index = 1;

        for (int i = 0; i < 5; i++) {
            String str = br.readLine();

            for (int j = 0; j < 9; j++) {
                char ch = str.charAt(j);

                if (ch == '.') {
                    continue;
                }

                if (ch == 'x') {
                    index++;
                } else {
                    arr[index++] = ch - 'A' + 1;
                    visited[ch - 'A' + 1] = true;
                }
            }
        }

        dfs(1);
    }

    private static boolean isValid() {
        if (arr[1] + arr[3] + arr[6] + arr[8] != 26) {
            return false;
        }

        if (arr[1] + arr[4] + arr[7] + arr[11] != 26) {
            return false;
        }

        if (arr[2] + arr[3] + arr[4] + arr[5] != 26) {
            return false;
        }

        if (arr[2] + arr[6] + arr[9] + arr[12] != 26) {
            return false;
        }

        if (arr[5] + arr[7] + arr[10] + arr[12] != 26) {
            return false;
        }

        if (arr[8] + arr[9] + arr[10] + arr[11] != 26) {
            return false;
        }

        return true;
    }

    private static void print() {
        for (int i = 0; i < 9; ++i) {
            if (i == 4) {
                System.out.print((char) (arr[1] + 'A' - 1));
            } else {
                System.out.print('.');
            }
        }
        System.out.println();

        for (int i = 0; i < 9; ++i) {
            if (i == 1) {
                System.out.print((char) (arr[2] + 'A' - 1));
            } else if (i == 3) {
                System.out.print((char) (arr[3] + 'A' - 1));
            } else if (i == 5) {
                System.out.print((char) (arr[4] + 'A' - 1));
            } else if (i == 7) {
                System.out.print((char) (arr[5] + 'A' - 1));
            } else {
                System.out.print('.');
            }
        }
        System.out.println();

        for (int i = 0; i < 9; ++i) {
            if (i == 2) {
                System.out.print((char) (arr[6] + 'A' - 1));
            } else if (i == 6) {
                System.out.print((char) (arr[7] + 'A' - 1));
            } else {
                System.out.print('.');
            }
        }
        System.out.println();

        for (int i = 0; i < 9; ++i) {
            if (i == 1) {
                System.out.print((char) (arr[8] + 'A' - 1));
            } else if (i == 3) {
                System.out.print((char) (arr[9] + 'A' - 1));
            } else if (i == 5) {
                System.out.print((char) (arr[10] + 'A' - 1));
            } else if (i == 7) {
                System.out.print((char) (arr[11] + 'A' - 1));
            } else {
                System.out.print('.');
            }
        }
        System.out.println();

        for (int i = 0; i < 9; ++i) {
            if (i == 4) {
                System.out.print((char) (arr[12] + 'A' - 1));
            } else {
                System.out.print('.');
            }
        }
        System.out.println();
    }

    private static void dfs(int index) {
        if (index == 13 || flag) {

            if (isValid()) {
                flag = true;
                print();
            }

            return;
        }

        // 이미 고정된 값이므로 백트래킹 할 필요없음. 그냥 분기 종료
        if (arr[index] != 0) {
            dfs(index + 1);
            return;
        }

        for (int i = 1; i < 13; i++) {
            if (visited[i]) continue;

            arr[index] = i;
            visited[i] = true;

            dfs(index + 1);

            arr[index] = 0;
            visited[i] = false;
        }
    }
}