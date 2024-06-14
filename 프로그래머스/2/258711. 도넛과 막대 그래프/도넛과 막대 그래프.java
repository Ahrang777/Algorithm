class Solution {
    static int N = 1_000_001;

    public int[] solution(int[][] edges) {
        int[] ingoing = new int[N]; // 진입 차수
        int[] outgoing = new int[N];    // 진출 차수
        for(int[] edge : edges) {
            outgoing[edge[0]]++;
            ingoing[edge[1]]++;
        }
        int created = 0;
        int eight = 0;
        int stick = 0;
        for(int i = 1; i < N; i++) {
            // 그래프 무조건 2개 이상이므로 진출차수 2이상, 진입차수 0인게 정점번호
            if(outgoing[i] >= 2) {
                if(ingoing[i] == 0) {   
                    created = i;
                } else { 
                    // 찾는 정점 번호 외에 진출 차수가 2개 이상인 경우는 8자 모양 그래프 밖에 없다. 
                    // 총 그래프는 정점 번호 created 의 진출 차수와 같다. 
                    eight++;
                }
            }else if(outgoing[i] == 0 && ingoing[i] > 0){   // 막대 그래프의 끝지점
                stick++;
            }
        }
        
        // 도넛, 막대, 8자 모양 그래프의 개수를 모두 더하면 시작 정점 created의 진출차수
        return new int[] {created, outgoing[created] - eight - stick, stick, eight};
    }
}