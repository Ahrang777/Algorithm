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
    
}