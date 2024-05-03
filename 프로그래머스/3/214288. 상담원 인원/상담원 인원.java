import java.util.*;
class Solution {
    static class Consulting {
        int start, time;

        public Consulting(int start, int time) {
            this.start = start;
            this.time = time;
        }
    }
    int min, K, N;
    int[] output;    // 1번 ~ K번 추가로 몇명 들어갈지 분배
    Map<Integer, List<Consulting>> map;
    public int solution(int k, int n, int[][] reqs) {
        min = Integer.MAX_VALUE;
        K = k;
        N = n;

        output = new int[k];
        map = new HashMap<>();

        for (int i = 1; i <= k; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int[] req : reqs) {
            int start = req[0];
            int time = req[1];
            int type = req[2];

            List<Consulting> list = map.get(type);
            list.add(new Consulting(start, time));
        }

        Arrays.fill(output, 1);

        dfs(0, K);

        return min;
    }

    /**
     *
     * @param cnt : 유형 idx
     * @param total : 추가로 분배한 멘토 숫자
     */
    private void dfs(int cnt, int total) {
        if (cnt == K) {
            if (total == N) {
                // output 내용을 통한 잔여시간 구하기
                // 최솟값 갱신
                min = Math.min(min, calculate());
            }

            return;
        }

        for (int i = 0; i <= N - total; i++) {
            output[cnt] += i;
            dfs(cnt + 1, total + i);
            output[cnt] -= i;
        }
    }
    
    private int calculate() {
        int result = 0;

        // 유형별 계산
        for (int i = 1; i <= K; i++) {
            PriorityQueue<Integer> endPQ = new PriorityQueue<>();
            List<Consulting> consultings = map.get(i);
            int mento = output[i - 1];

            for (Consulting c : consultings) {
                // 멘토 노는 경우
                if (endPQ.size() < mento) {
                    endPQ.offer(c.start + c.time);
                    continue;
                }

                // 멘토 모두 상담 진행 중
                int curTime = endPQ.poll();

                // 시작시간이 가장 최근 종료시간보다 작은 경우
                if (c.start < curTime) {
                    result += (curTime - c.start);
                } else {
                    curTime = c.start;
                }

                endPQ.offer(curTime + c.time);
            }
        }

        return result;
    }
}