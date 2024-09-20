import java.util.*;
class Solution {
    static class Node implements Comparable<Node> {
        int index, intensity;
        
        public Node (int index, int intensity) {
            this.index = index;
            this.intensity = intensity;
        }
        
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.intensity, o.intensity);
        }
    }
    static int N, min, minSummit;
    static int[] type;  // 0: 쉼터, 1: 출입구, 2: 산봉우리
    static List<Node>[] graph;
    static int[] intensities;
    static final int GATE = 1;
    static final int SUMMIT = 2;
    static final int INF = (int) 1e9;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        N = n;
        type = new int[N + 1];
        graph = new List[N + 1];
        intensities = new int[N + 1];
        min = minSummit = INF;
        
        for (int gate : gates) {
            type[gate] = GATE;
        }
        
        for (int summit : summits) {
            type[summit] = SUMMIT;
        }
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int intensity = path[2];
            
            // 여기서 intensity는 from ~ to사이 가중치로 사용
            graph[from].add(new Node(to, intensity));
            graph[to].add(new Node(from, intensity));
        }
        
        Arrays.fill(intensities, INF);
        dijkstra(gates, summits);
        
        return new int[]{minSummit, min};
    }
    
    private void dijkstra(int[] gates, int[] summits) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        // 여기서 intensity는 index까지의 intensity
        for (int gate : gates) {
            pq.offer(new Node(gate, 0));
            intensities[gate] = 0;
        }
        
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int now = node.index;
            int intensity = node.intensity;
            
            // 우선순위큐를 이용하여 최솟값으로 확인해 나갔고 이미 방문 했으니 continue;
            if (type[now] == SUMMIT)    continue;
            
            if (intensities[now] < intensity)   continue;
            
            for (Node next : graph[now]) {
                int nIndex = next.index;
                int nIntensity = Math.max(next.intensity, intensity);
                
                if (nIntensity < intensities[nIndex]) {
                    intensities[nIndex] = nIntensity;
                    pq.offer(new Node(nIndex, nIntensity));
                }
            }
        }
        
        // 작은 번호의 산봉우리부터 확인하기 때문에 min == intensities[summit] 인경우 갱신은 안해도 된다. 
        Arrays.sort(summits);
        for (int summit : summits) {
            if (intensities[summit] < min) {
                min = intensities[summit];
                minSummit = summit;
            } 
        }
    }
}