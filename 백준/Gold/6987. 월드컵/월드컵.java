import java.io.*;
import java.util.*;

public class Main {
    // 각 나라별 승, 무, 패 기록 / 각 경기별 나라 매칭
    static int[][] worldCup, matchGame;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        // 총 게임 횟수
        int totalGame = 0;
        for (int i = 1; i < 6; i++) {
            totalGame += i;
        }

        for (int tc = 0; tc < 4; tc++) {
            worldCup = new int[6][3];
            boolean isCorrect = true;

            matchGame = new int[totalGame][2];
            int index = 0;  // 매칭된 게임 순서

            for (int i = 0; i < 5; i++) {
                for (int j = i + 1; j < 6; j++) {
                    matchGame[index][0] = i;
                    matchGame[index][1] = j;
                    index++;
                }
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 6; i++) {
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());

                worldCup[i][0] = win;
                worldCup[i][1] = draw;
                worldCup[i][2] = lose;

                // 승 + 무 + 패 = 5 가 나와야 한다. 한 국가당 5경기 진행
                if (win + draw + lose != 5) {
                    isCorrect = false;
                }
            }

            if (!isCorrect) {   // 나올 수 없는 결과
                sb.append(0).append(" ");
            } else {    // 나올 수 있는 결과
                if (isPossibleResult(0, totalGame)) {
                    sb.append(1).append(" ");
                } else {    // 모든 나라 경기 제대로 못
                    sb.append(0).append(" ");
                }
            }
        }

        System.out.println(sb);
    }

    // 서로 다른 나라와 한 번씩 승부할 때 각 나라의 승, 무, 패 조합이 가능한 경우인지 확인
    private static boolean isPossibleResult(int cnt, int totalGame) {
        if (cnt == totalGame) {
            return true;
        }

        int homeTeam = matchGame[cnt][0];
        int awayTeam = matchGame[cnt][1];

        // 홈팀 승, 원정팀 패
        if (worldCup[homeTeam][0] > 0 && worldCup[awayTeam][2] > 0) {
            worldCup[homeTeam][0]--;
            worldCup[awayTeam][2]--;

            if (isPossibleResult(cnt + 1, totalGame)) {
                return true;
            }

            worldCup[homeTeam][0]++;
            worldCup[awayTeam][2]++;
        }

        // 홈팀, 원정팀 무승부
        if (worldCup[homeTeam][1] > 0 && worldCup[awayTeam][1] > 0) {
            worldCup[homeTeam][1]--;
            worldCup[awayTeam][1]--;

            if (isPossibleResult(cnt + 1, totalGame)) {
                return true;
            }

            worldCup[homeTeam][1]++;
            worldCup[awayTeam][1]++;
        }

        // 홈팀 패, 원정팀 승
        if (worldCup[homeTeam][2] > 0 && worldCup[awayTeam][0] > 0) {
            worldCup[homeTeam][2]--;
            worldCup[awayTeam][0]--;

            if (isPossibleResult(cnt + 1, totalGame)) {
                return true;
            }

            worldCup[homeTeam][2]++;
            worldCup[awayTeam][0]++;
        }

        return false;
    }
}