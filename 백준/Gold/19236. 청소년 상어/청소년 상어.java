import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Shark {
        int x, y, dir, eatSum;

        Shark() { }

        Shark(int x, int y, int dir, int eatSum) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.eatSum = eatSum;
        }
    }

    static class Fish {
        int x, y, index, dir;
        boolean isAlive = true;

        Fish() { }

        Fish(int x, int y, int index, int dir, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.index = index;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxSum = 0;

    static final int BLANK = 0;
    static final int SHARK = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] arr = new int[4][4];
        List<Fish> fishes = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < 4; j++) {
                Fish f = new Fish();
                f.index = Integer.parseInt(st.nextToken());
                f.dir = Integer.parseInt(st.nextToken()) - 1;
                f.x = i;
                f.y = j;

                fishes.add(f);
                arr[i][j] = f.index;
            }
        }

        Collections.sort(fishes, ((o1, o2) -> Integer.compare(o1.index, o2.index)));

        Fish f = fishes.get(arr[0][0] - 1);
        Shark shark = new Shark(0, 0, f.dir, f.index);
        f.isAlive = false;
        arr[0][0] = SHARK;

        dfs(arr, shark, fishes);
        System.out.println(maxSum);
    }

    private static void dfs(int[][] arr, Shark shark, List<Fish> fishes) {
        if (maxSum < shark.eatSum) {
            maxSum = shark.eatSum;
        }

        fishes.forEach(f -> moveFish(f, arr, fishes));

        // 상어 이동
        for (int dist = 1; dist < 4; dist++) {
            int nx = shark.x + dx[shark.dir] * dist;
            int ny = shark.y + dy[shark.dir] * dist;

            // 상어 이동 가능한 경우
            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > BLANK) {
                int[][] arrCopies = copyArr(arr);
                List<Fish> fishCopies = copyFishes(fishes);

                arrCopies[shark.x][shark.y] = BLANK;
                Fish f = fishCopies.get(arr[nx][ny] - 1);   // 상어가 먹을 물고기
                Shark newShark = new Shark(f.x, f.y, f.dir, shark.eatSum + f.index);
                f.isAlive = false;
                arrCopies[f.x][f.y] = -1;

                dfs(arrCopies, newShark, fishCopies);
            }
        }
    }

    private static void moveFish(Fish fish, int[][] arr, List<Fish> fishes) {
        if (fish.isAlive == false) return;

        for (int i = 0; i < 8; i++) {
            int dir = (fish.dir + i) % 8;
            int nx = fish.x + dx[dir];
            int ny = fish.y + dy[dir];

            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > SHARK) {
                arr[fish.x][fish.y] = BLANK;

                if (arr[nx][ny] == BLANK) {
                    fish.x = nx;
                    fish.y = ny;
                } else {
                    Fish temp = fishes.get(arr[nx][ny] - 1);
                    temp.x = fish.x;
                    temp.y = fish.y;
                    arr[fish.x][fish.y] = temp.index;

                    fish.x = nx;
                    fish.y = ny;
                }

                arr[nx][ny] = fish.index;
                fish.dir = dir;
                return;
            }
        }
    }

    private static int[][] copyArr(int[][] arr) {
        int[][] temp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        return temp;
    }

    private static List<Fish> copyFishes(List<Fish> fishes) {
        List<Fish> temp = new ArrayList<>();
        fishes.forEach(e -> temp.add(new Fish(e.x, e.y, e.index, e.dir, e.isAlive)));
        return temp;
    }
}