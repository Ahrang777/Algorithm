import java.io.*;
import java.util.*;

public class Main{
    static int N, M, mid;
    static int[][] map;
    static int[][] attack;
    static List<Integer> list;
    static int[] dr = {0, -1, 1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, 0, -1, 1};
    static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        attack = new int[M][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            attack[i][0] = Integer.parseInt(st.nextToken());    // d
            attack[i][1] = Integer.parseInt(st.nextToken());    // s
        }
        mid = N / 2;

        list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int d = attack[i][0];
            int s = attack[i][1];
            list.clear();
            attack(d, s);
            move();
            remove();
            map = new int[N][N];
            divide();
        }
        System.out.println(sum);
    }

    private static void attack(int d, int s) {
        for (int i = 1; i <= s; i++) {
            int nr = mid + dr[d] * i;
            int nc = mid + dc[d] * i;
            if (canMove(nr, nc)) {
                map[nr][nc] = 0;
            } else {
                break;
            }
        }
    }

    private static boolean canMove(int r, int c) {
        return (r >= 0 && c >= 0 && r < N && c < N);
    }

    private static void move() {
        int cnt = 0;
        int size = 1;
        int corner = 0;
        int dir = 3;
        int nr = mid;
        int nc = mid;
        while (true) {
            cnt++;
            nr += dr[dir];
            nc += dc[dir];

            if (nr == 0 && nc == -1) {
                break;
            }

            if (map[nr][nc] != 0) {
                list.add(map[nr][nc]);
            }

            if (cnt == size) {
                corner++;
                dir = dirChange(dir);
                cnt = 0;
            }

            if (corner == 2) {
                corner = 0;
                size++;
            }
        }
    }

    private static void remove() {
        int[][] removeList = new int[N * N][2];

        int size = list.size();
        int connectCnt = 1;
        int removeCnt = 0;
        boolean isRemove = false;
        for (int i = 0; i < size - 1; i++) {
            if (list.get(i) == list.get(i + 1)) {
                connectCnt++;
            } else {
                if (connectCnt >= 4) {
                    removeList[removeCnt][0] = i - connectCnt + 1;
                    removeList[removeCnt][1] = connectCnt;
                    removeCnt++;
                    isRemove = true;
                }
                connectCnt = 1;
            }
        }

        if (connectCnt >= 4) {
            removeList[removeCnt][0] = size - connectCnt;
            removeList[removeCnt][1] = connectCnt;
            removeCnt++;
            isRemove = true;
        }

        if (isRemove) {
            for (int k = removeCnt - 1; k >= 0; k--) {
                int start = removeList[k][0];
                int len = removeList[k][1];

                for (int i = 0; i < len; i++) {
                    sum += list.get(start);
                    list.remove(start);
                }
            }
        }

        if (isRemove) {
            remove();
        }
    }

    private static void divide() {
        ArrayList<Integer> newList = new ArrayList<>();

        int size = list.size();
        int connectCnt = 1;
        for (int i = 0; i < size - 1; i++) {
            if (list.get(i) == list.get(i + 1)) {
                connectCnt++;
            } else {
                newList.add(connectCnt);
                newList.add(list.get(i));
                connectCnt = 1;
            }
        }

        // 마지막 처리
        if (size >= 1) {
            newList.add(connectCnt);
            newList.add(list.get(size - 1));
        }

        int cnt = 0;
        size = 1;
        int corner = 0;
        int dir = 3;
        int nr = mid;
        int nc = mid;
        int time = newList.size();
        int total = 0;
        while (time-- > 0) {
            cnt++;
            nr += dr[dir];
            nc += dc[dir];

            if (nr == 0 && nc == -1) {
                break;
            }

            map[nr][nc] = newList.get(total++);

            if (cnt == size) {
                corner++;
                dir = dirChange(dir);
                cnt = 0;
            }

            if (corner == 2) {
                corner = 0;
                size++;
            }
        }
    }

    private static int dirChange(int d) {
        if (d==3) return 2;
        else if (d==2) return 4;
        else if (d==4) return 1;
        else if (d==1) return 3;
        return 0;
    }
}