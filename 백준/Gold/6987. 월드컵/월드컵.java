import java.io.*;
import java.util.*;

public class Main {
    static int[][] worldCup, matchGame;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int totalGame = 0;
        for (int i = 1; i < 6; i++) {
            totalGame += i;
        }

        for (int tc = 0; tc < 4; tc++) {
            worldCup = new int[6][3];
            boolean isCorrect = true;

            int index = 0;
            matchGame = new int[totalGame][2];

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

                if (win + draw + lose != 5) {
                    isCorrect = false;
                }
            }

            if (isCorrect) {
                if (isValid(0, totalGame)) {
                    sb.append(1).append(" ");
                } else {
                    sb.append(0).append(" ");
                }
            } else {
                sb.append(0).append(" ");
            }
        }

        System.out.println(sb);
    }

    private static boolean isValid(int cnt, int totalGame) {
        if (cnt == totalGame) {
            return true;
        }

        int homeTeam = matchGame[cnt][0];
        int awayTeam = matchGame[cnt][1];

        // 홈팀 승, 원정팀 패
        if (worldCup[homeTeam][0] > 0 && worldCup[awayTeam][2] > 0) {
            worldCup[homeTeam][0]--;
            worldCup[awayTeam][2]--;

            if (isValid(cnt + 1, totalGame)) {
                return true;
            }

            worldCup[homeTeam][0]++;
            worldCup[awayTeam][2]++;
        }

        // 홈팀, 원정팀 무승부
        if (worldCup[homeTeam][1] > 0 && worldCup[awayTeam][1] > 0) {
            worldCup[homeTeam][1]--;
            worldCup[awayTeam][1]--;

            if (isValid(cnt + 1, totalGame)) {
                return true;
            }

            worldCup[homeTeam][1]++;
            worldCup[awayTeam][1]++;
        }

        // 홈팀 패, 원정팀 승
        if (worldCup[homeTeam][2] > 0 && worldCup[awayTeam][0] > 0) {
            worldCup[homeTeam][2]--;
            worldCup[awayTeam][0]--;

            if (isValid(cnt + 1, totalGame)) {
                return true;
            }

            worldCup[homeTeam][2]++;
            worldCup[awayTeam][0]++;
        }

        return false;
    }
}