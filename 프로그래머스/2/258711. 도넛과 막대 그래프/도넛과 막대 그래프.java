import java.util.*;
class Solution {
    static final int L = 1000000;
    List<Integer>[] adjList = new ArrayList[L + 1];
    int[] outDegree = new int[L + 1];
    int[] inDegree = new int[L + 1];
    boolean[] isVisited = new boolean[L + 1];

    public int[] solution(int[][] edges) {
        for (int i = 0; i <= L; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            adjList[from].add(to);
            inDegree[to]++;
            outDegree[from]++;
        }
        int start = 0;
        for (int i = 1; i <= L; i++) {
            if (outDegree[i] >= 2 && inDegree[i] == 0) {
                start = i;
                break;
            }
        }
        int[] answer = new int[]{start, 0, 0, 0};
        for (int now : adjList[start]) {
            int cycleCount = findCycleCount(now);

            if (cycleCount == 0) {
                answer[2]++;
            } else if (cycleCount == 2) {
                answer[3]++;
            } else {
                answer[1]++;
            }
        }
        return answer;
    }

    private int findCycleCount(int s) {
        Deque<Integer> q = new ArrayDeque<>();
        q.add(s);
        isVisited[s] = true;

        int cycleCount = 0;
        while(!q.isEmpty()) {
            int now = q.poll();
            for (int next : adjList[now]) {
                if (isVisited[next]) {
                    cycleCount++;
                    continue;
                }
                q.add(next);
                isVisited[next] = true;
            }
        }
        return cycleCount; 
    }
    
//     static int N = 1_000_001;

//     public int[] solution(int[][] edges) {
//         int[] ingoing = new int[N]; // 진입 차수
//         int[] outgoing = new int[N];    // 진출 차수
//         for(int[] edge : edges) {
//             outgoing[edge[0]]++;
//             ingoing[edge[1]]++;
//         }
//         int created = 0;
//         int eight = 0;
//         int stick = 0;
//         for(int i = 1; i < N; i++) {
//             // 그래프 무조건 2개 이상이므로 진출차수 2이상, 진입차수 0인게 정점번호
//             if(outgoing[i] >= 2) {
//                 if(ingoing[i] == 0) {   
//                     created = i;
//                 } else { 
//                     // 찾는 정점 번호 외에 진출 차수가 2개 이상인 경우는 8자 모양 그래프 밖에 없다. 
//                     // 총 그래프는 정점 번호 created 의 진출 차수와 같다. 
//                     eight++;
//                 }
//             }else if(outgoing[i] == 0 && ingoing[i] > 0){   // 막대 그래프의 끝지점
//                 stick++;
//             }
//         }
        
//         // 도넛, 막대, 8자 모양 그래프의 개수를 모두 더하면 시작 정점 created의 진출차수
//         return new int[] {created, outgoing[created] - eight - stick, stick, eight};
//     }
}