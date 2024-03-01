import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Fish {
        int index, x, y, dir;
        boolean isAlive;

        public Fish(int index, int x, int y, int dir, boolean isAlive) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }

    static class Shark {
        int x, y, dir, eat;

        public Shark(int x, int y, int dir, int eat) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.eat = eat;
        }
    }

    static int[][] map, copiedMap;
    static List<Fish> fishes, copiedFishes;
    static int max;
    static Shark s;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        map = new int[4][4];
        fishes = new ArrayList<>();
        max = -1;

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int index = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                fishes.add(new Fish(index, i, j, dir, true));
                map[i][j] = index;
            }
        }

        Collections.sort(fishes, (o1, o2) -> Integer.compare(o1.index, o2.index));

        Fish f = fishes.get(map[0][0] - 1);
        s = new Shark(0, 0, f.dir, f.index);
        f.isAlive = false;
        map[0][0] = 0;
        dfs(s, map, fishes);

        System.out.println(max);
    }

    private static void dfs(Shark s, int[][] map, List<Fish> fishes) {
        max = Math.max(max, s.eat);

        // 물고기 이동
        for (Fish f : fishes) {
            if (!f.isAlive) {
                continue;
            }

            for (int d = 0; d < 8; d++) {
                int nd = (f.dir + d) % 8;
                int nx = f.x + dx[nd];
                int ny = f.y + dy[nd];

                if (!isRange(nx, ny) || nx == s.x && ny == s.y) {
                    continue;
                }

                map[f.x][f.y] = 0;
                if (map[nx][ny] != 0) {
                    Fish nf = fishes.get(map[nx][ny] - 1);
                    nf.x = f.x;
                    nf.y = f.y;
                    map[f.x][f.y] = nf.index;
                }

                f.x = nx;
                f.y = ny;
                f.dir = nd;
                map[nx][ny] = f.index;
                break;
            }
        }

        // 상어 이동
        for (int i = 1; i < 4; i++) {
            int nx = s.x + dx[s.dir] * i;
            int ny = s.y + dy[s.dir] * i;

            if (!isRange(nx, ny) || map[nx][ny] == 0) {
                continue;
            }

            copyMap(map);
            // 리스트 복사
            copyFishes(fishes);

            Fish target = copiedFishes.get(copiedMap[nx][ny] - 1);
            copiedMap[nx][ny] = 0;
            target.isAlive = false;
            dfs(new Shark(nx, ny, target.dir, s.eat + target.index), copiedMap, copiedFishes);
        }
    }

    private static void copyMap(int[][] map) {
        copiedMap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copiedMap[i][j] = map[i][j];
            }
        }
    }

    private static void copyFishes(List<Fish> fishes) {
        copiedFishes = new ArrayList<>();
        for (Fish f : fishes) {
            copiedFishes.add(new Fish(f.index, f.x, f.y, f.dir, f.isAlive));
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }
}