import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int V, E, A, B, size;    // 정점개수, 간선개수, 정점1, 정점2, 가장 가까운 공통조상 노드를 루트노드로 하는 서브트리 크기
    static int[] parents;   // 각 노드의 부모를 기록할 배열
    static List<Integer>[] children;    // 각 노드의 자식 노드들을 기록할 배열

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        parents = new int[10001];
        children = new ArrayList[10001];

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= V; i++) {
                children[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                parents[child] = parent;
                children[parent].add(child);
            }

            // 공통조상 찾기
            List<Integer> aCommons = new ArrayList<>();
            List<Integer> bCommons =  new ArrayList<>();

            addParents(A, aCommons);
            addParents(B, bCommons);

            // 가장 가까운 공통조상 찾기
            // 아래에서 위로 조상노드들을 List에 담기때문에 가장 마지막에 담기는 조상노드는 루트노드인 1이다.
            // 따라서 먼저 같다고 나온 공통조상노드가 가장 가까운 공통조상노드이다. 
            int minCommon = 1;
            for (Integer aCommon : aCommons) {
                if (bCommons.contains(aCommon)) {
                    minCommon = aCommon;
                    break;
                }
            }

            // 크기 구하기
            // 공통조상 노드가 1(루트 노드)일 경우는 서브트리는 현재 전체 트리이므로 서브트리의 크기는 정점의 개수인 V이다.
            size = V;
            
            // 공통 조상노드가 1이 아닌 경우는 직접 계산
            if (minCommon != 1) {
                size = bfs(minCommon);
            }

            sb.append("#").append(tc).append(" ").append(minCommon).append(" ").append(size).append("\n");
        }

        System.out.println(sb);
    }

    private static int bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();

        // children 에는 자식노드만 작성되어있고 해당 자식 노드까지의 경로는 유일하기에 
        // 한번 간 경로를 다시 갈 경우는 없다
        // 따라서 bfs임에도 방문처리를 하지 않아도 된다.
        q.offer(start);
        int cnt = 0;

        while (!q.isEmpty()) {
            Integer now = q.poll();
            cnt++;

            for (int next : children[now]) {
                q.offer(next);
            }
        }

        return cnt;
    }

    private static void addParents(int start, List<Integer> commons) {
        // 부모노드가 0인 경우는 루트노드까지 고려가 끝난 경우
        if (parents[start] == 0) {
            return;
        }

        commons.add(start);
        addParents(parents[start], commons);
    }
}