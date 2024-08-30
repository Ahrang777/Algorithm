import java.util.*;

class Solution {
    int N;
    List<int[]> diceComb = new ArrayList<>();
    public int[] solution(int[][] dice) {
        int[] answer = {};
        int winMax = 0;
        
        N = dice.length;
        diceCombination(0, 0, new int[N / 2]);
        
        for (int[] diceA : diceComb) {
            List<Integer> scoresA = new ArrayList<>();
            scoreCombination(0, 0, dice, diceA, scoresA);
            Collections.sort(scoresA);
            
            int[] diceB = getDiceB(diceA);
            List<Integer> scoresB = new ArrayList<>();
            scoreCombination(0, 0, dice, diceB, scoresB);
            Collections.sort(scoresB);
            
            int winCnt = getWinCnt(scoresA, scoresB);
            
            if (winCnt > winMax) {
                winMax = winCnt;
                answer = diceA;
            }
        }
        
        for (int i = 0; i < N / 2; i++) {
            answer[i]++;
        }
        
        return answer;
    }
    
    private int getWinCnt(List<Integer> scoresA, List<Integer> scoresB) {
        int total = 0;
        
        for (int score : scoresA) {
//             int s = 0;
//             int e = scoresB.size() - 1;
            
//             while (s <= e) {
//                 int mid = (s + e) / 2;
                
//                 if (score > scoresB.get(mid)) {
//                     s = mid + 1;
//                 } else {
//                     e = mid - 1;
//                 }
//             }
            
//             total += s;
            
            // lowerBound 처리 A 점수보다 처음 같거나 큰 값의 위치 index를 구한다.
            // 0 ~ index - 1 까지가 A가 이기는 경우이다.
            // index - 1 - 0 + 1 = index 개가 이기는 경우이다. 
            int s = 0;
            int e = scoresB.size();
            
            while (s < e) {
                int mid = (s + e) / 2;
                
                if (score <= scoresB.get(mid)) {
                    e = mid;                
                } else {
                    s = mid + 1;
                }
            }
            
            total += s;
        }
        
        return total;
    }
    
    private int[] getDiceB(int[] otherDice) {
        int[] dices = new int[N / 2];
        boolean[] isSelected = new boolean[N];
        
        for (int o : otherDice) {
            isSelected[o] = true;
        }
        
        int index = 0;
        for (int i = 0; i < N; i++) {
            if (isSelected[i]) continue;
            
            dices[index++] = i;
        }
        
        return dices;
    }
    
    private void scoreCombination(int cnt, int sum, int[][] dice, int[] diceComb, List<Integer> scores) {
        if (cnt == N / 2) {
            scores.add(sum);
            return;
        }
        
        for (int i = 0; i < 6; i++) {
            scoreCombination(cnt + 1, sum + dice[diceComb[cnt]][i], dice, diceComb, scores);
        }
    }
    
    private void diceCombination(int cnt, int start, int[] numbers) {
        if (cnt == N / 2) {
            diceComb.add(numbers.clone());
            return;
        }
        
        for (int i = start; i < N; i++) {
            numbers[cnt] = i;
            diceCombination(cnt + 1, i + 1, numbers);
        }
    }
}