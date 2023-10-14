import java.io.*;
import java.util.*;

public class Main{
    static class Combination {
        int n;
        int r;
        int[] now;
        ArrayList<ArrayList<Integer>> result;

        public Combination(int n, int r) {
            this.n = n;
            this.r = r;
            now = new int[r];
            result = new ArrayList<>();
        }

        public void comb(int depth, int index, int target) {
            if (depth == r) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int i : now) {
                    tmp.add(i);
                }
                result.add(tmp);

                return;
            }
            if (target == n) {
                return;
            }

            now[index] = target;
            comb(depth + 1, index + 1, target + 1);
            comb(depth, index, target + 1);
        }

        public ArrayList<ArrayList<Integer>> getResult() {
            return result;
        }
    }

    public static int n;
    public static int[][] map = new int[20][20];
    public static boolean[] visit = new boolean[20];
    public static int minValue = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;

        n = Integer.parseInt(bf.readLine());

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(bf.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // nCn/2
        comb(0, 0);
        System.out.println(minValue);
    }

    // idx는 인덱스, count는 조합 개수(=재귀 깊이)
    static void comb(int idx, int count) {
        // 팀 조합이 완성될 경우
        if(count == n / 2) {
			/*
			 방문한 팀과 방문하지 않은 팀을 각각 나누어
			 각 팀의 점수를 구한 뒤 최솟값을 찾는다.
			*/
            diff();
            return;
        }

        for(int i = idx; i < n; i++) {
            // 방문하지 않았다면?
            if(!visit[i]) {
                visit[i] = true;	// 방문으로 변경
                comb(i + 1, count + 1);	// 재귀 호출
                visit[i] = false;	// 재귀가 끝나면 비방문으로 변경
            }
        }
    }

    // 두 팀의 능력치 차이를 계산하는 함수
    static void diff() {
        int team_start = 0;
        int team_link = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // i 번째 사람과 j 번째 사람이 true라면 스타트팀으로 점수 플러스
                if (visit[i] == true && visit[j] == true) {
                    team_start += map[i][j];
                    team_start += map[j][i];
                }
                // i 번째 사람과 j 번째 사람이 false라면 링크팀으로 점수 플러스
                else if (visit[i] == false && visit[j] == false) {
                    team_link += map[i][j];
                    team_link += map[j][i];
                }
            }
        }
        // 두 팀의 점수 차이 (절댓값)
        int val = Math.abs(team_start - team_link);

		/*
		  두 팀의 점수차가 0이라면 가장 낮은 최솟값이기 때문에
		  더이상의 탐색 필요없이 0을 출력하고 종료하면 된다.
		 */
        if (val == 0) {
            System.out.println(val);
            System.exit(0);
        }

        minValue = Math.min(val, minValue);
    }
}