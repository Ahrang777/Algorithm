class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        int N = queue1.length;
        long sum1 = 0;
        long sum2 = 0;
        
        for (int i = 0; i < N; i++) {
            sum1 += queue1[i];
            sum2 += queue2[i];
        }
        
        // q1, q2의 원소 합이 홀수인 경우 반, 반 나눠지는 경우 존재X 
        // ex) 9 >> 4, 5 어떻게 해도 같아질 수 없다. 4 != 5
        if ((sum1 + sum2) % 2 == 1) {
            return -1;
        }
        
        long total = (sum1 + sum2) / 2;
        int[] arr = new int[2 * N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = queue1[i];
        }
        
        for (int i = 0; i < N; i++) {
            arr[i + N] = queue2[i];
        }
        
        // 투포인터
        // s, e가 이동할 때마다 작업횟수 + 1
        // total 이 만들어지면 종료
        // s == e 일 때까지 total이 되지 못하면 -1
        // q1 뒤에 q2를 붙여서 배열을 만든 뒤 s는 q1의 시작점, e는 q2의 시작점으로 지정
        // q1의 합계를 이용하여 sum을 만든다.
        // q1의 합계인 sum과 total(절반값)을 비교하며 s, e조절
        // s가 움직인 것은 q1 -> q2로 원소를 꺼내서 집어넣은것
        // e가 움직인 것은 q2 -> q1으로 원소를 꺼내서 집어넣은것
        // s, e만 움직이고 실제로 원소는 움직이지 않아도 된다. 
        // 해당 배열에서 끝까지 가서 s==e가 된경우는 모든 경우를 다 확인한것. 
        // 이후에는 어차피 큐라서 다시 순서가 반복되는것 뿐이다.
        
        int s = 0;  // q1 시작점
        int e = N;  // q2 시작점
        long sum = sum1; // q1의 원소 합계
        
        while (s != e) {
            if (sum == total) {
                break;
            }
            
            // 값 완성 안되었는데 더이상 이동x
            if (sum < total && e == 2 * N - 1) {
                break;
            }
            
            if (sum > total) {
                sum -= arr[s];
                s++;
            } else if (sum < total) {
                sum += arr[e];
                e++;
            } 
            
            answer++;
        }
        
        if (sum != total) {
            answer = -1;
        }
        
        return answer;
    }
}