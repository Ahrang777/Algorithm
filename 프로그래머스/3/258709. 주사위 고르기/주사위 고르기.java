import java.util.*;

class Solution {
    private int n;
    private List<int[]> diceComb = new ArrayList<>();

    public int[] solution(int[][] dice) {
        this.n = dice.length;
        combineDice(0, 0, new int[n / 2]); // (1)

        int[] answer = {};
        int maxWinCnt = 0;
        for (int[] aComb : diceComb) {
            List<Integer> aScores = new ArrayList<>();
            combineScores(0, 0, aComb, aScores, dice); // (2)
            Collections.sort(aScores);

            int[] bComb = getBComb(aComb);
            List<Integer> bScores = new ArrayList<>();
            combineScores(0, 0, bComb, bScores, dice); // (3)
            Collections.sort(bScores);

            int winCnt = getWinCntOfA(aScores, bScores); // (4)
            if (maxWinCnt < winCnt) {
                maxWinCnt = winCnt;
                answer = aComb;
            }
        }

        for (int i = 0; i < answer.length; i++) {
            answer[i]++; // (5)
        }
        return answer;
    }

    private void combineDice(int start, int cnt, int[] numbers) { 
        if (cnt == n / 2) { // (6)
            diceComb.add(numbers.clone());
            return;
        }

        for (int i = start; i < n; i++) {
            numbers[cnt] = i;
            combineDice(i + 1, cnt + 1, numbers);
        }
    }

    private int[] getBComb(int[] aComb) { // (7)
        boolean[] isA = new boolean[n];
        for (int num : aComb) {
            isA[num] = true; // (8)
        }

        int b = 0;
        int[] bComb = new int[n / 2];
        for (int i = 0; i < n; i++) {
            if (!isA[i]) {
                bComb[b] = i;
                b++;
            }
        }

        return bComb;
    }

    private void combineScores(int cnt, int sum, int[] comb, List<Integer> scores, int[][] dice) {
        if (cnt == n / 2) { // (9)
            scores.add(sum);
            return;
        }

        for (int i = 0; i < 6; i++) {
            combineScores(cnt + 1, sum + dice[comb[cnt]][i], comb, scores, dice);
        }
    }

    private int getWinCntOfA(List<Integer> aScores, List<Integer> bScores) {
        int winCnt = 0;
        for (int s : aScores) { // (10)
            int start = 0;
            int end = bScores.size() - 1;
            while (start <= end) {
                int mid = (start + end) / 2;

                if (s > bScores.get(mid)) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            winCnt += start;
        }
        return winCnt;
    }
}