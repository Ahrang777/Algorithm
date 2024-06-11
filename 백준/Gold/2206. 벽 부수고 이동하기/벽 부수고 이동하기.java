import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] check; //해당 좌표를 최초로 지나갈 때의 벽 뚫은 횟수
    static boolean[][] map; //벽과 길이 표시된 맵
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = 1;

        //예외처리 (출발점==목적지인 경우)
        if(N==1 && M==1) {
            System.out.println(1);
            return;
        }

        map = new boolean[N+2][M+2]; //외각패딩까지 +2 사이즈만큼 할당
        check = new int[N+2][M+2];

        Arrays.fill(check[0], -1); //외각패딩 -1로 초기화
        Arrays.fill(check[N+1], -1);

        for(int i=1; i<=N; i++) {
            Arrays.fill(check[i], Integer.MAX_VALUE);
            check[i][0] = -1;   //외각패딩 -1로 초기화
            check[i][M+1] = -1; //외각패딩 -1로 초기화

            String str = br.readLine();
            for(int j=1; j<=M; j++) {
                map[i][j] = str.charAt(j - 1) == '1';
            }
        }

        Queue<int[]> que = new ArrayDeque<>();
        que.offer(new int[] {1,1,0});   // y, x, 벽 부순 횟수
        check[1][1] = 0;

        int depth = 1;
        while(!que.isEmpty()) {
            int size = que.size();
            depth++;

            while(size-->0) {
                int[] cur = que.poll();
                int v = cur[2];

                for(int d=0; d<4; d++) {
                    int ny = cur[0] + dy[d];
                    int nx = cur[1] + dx[d];

                    if(check[ny][nx] > v) check[ny][nx] = v; //벽을 더 적게 부셔서 도달할 수 있으면 계속 진행
                    else continue; //벽을 더 많이 부시는 경우는 필요없으니 패스 (+ 동시에 범위 판단도 함)
                    //벽이면
                    if(map[ny][nx]) {
                        if(v == K) continue; //뚫을 수 있는 횟수 다 사용했으면 패스
                        if(ny!=N || nx!=M) que.add(new int[] {ny, nx, v+1}); //목적지가 아니면 계속 진행
                        else {
                            System.out.println(depth);
                            return;
                        }
                        continue;
                    }
                    //길이면
                    if(ny!=N || nx!=M) que.add(new int[] {ny, nx, v}); //목적지가 아니면 계속 진행
                    else {
                        System.out.println(depth);
                        return;
                    }
                }
            }
        }

        System.out.println(-1);
    }

    /*static class Node {
        int x, y, brokenWallCnt;

        public Node(int x, int y, int brokenWallCnt) {
            this.x = x;
            this.y = y;
            this.brokenWallCnt = brokenWallCnt;
        }
    }
    static int N, M, min, map[][], v[][][];

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new ArrayDeque<>();
        int[][][] v = new int[2][N][M];   // 벽 부순 개수, x, y
        q.offer(new Node(x, y, 0));


        v[0][x][y] = 1;

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == N - 1 && now.y == M - 1) {
                min = v[now.brokenWallCnt][now.x][now.y];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (!isRange(nx, ny)) {
                    continue;
                }

                // 다음이 벽, 아직 벽을 부순적 없는 경우
                if (map[nx][ny] == 1 && now.brokenWallCnt == 0) {
                    q.offer(new Node(nx, ny, 1));
                    v[1][nx][ny] = v[0][now.x][now.y] + 1;
                }

                // 다음이 빈칸
                if (map[nx][ny] == 0 && v[now.brokenWallCnt][nx][ny] == 0) {
                    q.offer(new Node(nx, ny, now.brokenWallCnt));
                    v[now.brokenWallCnt][nx][ny] = v[now.brokenWallCnt][now.x][now.y] + 1;
                }
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }*/
}