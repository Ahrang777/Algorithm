import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N; // 식재료 개수
    static int min[]; // 최소로 얻어야 하는 영양소
    static int foodInfo[][]; // 각 식재료의 영양 정보와 가격
    static int minCost = Integer.MAX_VALUE; // 최소 비용
    static List<Integer> selectedFood; // 선택된 식재료의 번호를 저장하는 리스트
    static String result; // 최종 결과를 저장하는 문자열
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine()); // 식재료 개수 입력
        min = new int[4]; // 최소 영양소 배열 초기화

        st = new StringTokenizer(br.readLine()); // 최소 영양소 입력 받기
        for (int i = 0; i < 4; i++) {
            min[i] = Integer.parseInt(st.nextToken());
        }

        foodInfo = new int[N + 1][5]; // 각 식재료의 정보를 담는 배열 초기화
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine()); // 식재료 정보 입력 받기
            for (int j = 0; j < 5; j++) {
                foodInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int total[] = new int[4]; // 선택된 식재료의 영양소 합을 저장하는 배열 초기화
        selectedFood = new ArrayList<>(); // 선택된 식재료의 번호를 저장할 리스트 초기화
        powerset(1, 0, total); // 조합 생성 및 검사 함수 호출

        // 결과 출력
        if (result == null) {
            System.out.println(-1); // 조건을 만족하는 식재료가 없는 경우 -1 출력
        } else {
            System.out.println(minCost); // 최소 비용 출력
            System.out.println(result); // 선택된 식재료 번호 출력
        }
    }

    // powerset 함수: 조합 생성 및 검사
    private static void powerset(int foodIdx, int totalCost, int[] total) {
        if (totalCost > minCost) return; // 현재 비용이 최소 비용보다 크면 중단

        if (foodIdx > N) { // 모든 식재료를 고려했을 때
            for (int i = 0; i < 4; i++) {
                if (min[i] > total[i]) return; // 최소 영양소를 만족하지 못하면 중단
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < selectedFood.size(); i++) {
                sb.append(selectedFood.get(i) + " "); // 선택된 식재료 번호 저장
            }

            if (totalCost == minCost) { // 현재 비용이 최소 비용과 같을 때
                // result의 아스키코드 - sb의 아스키코드 => 음수이면 sb가 더 크다는 뜻이니까 사전순으로 뒤여야함.
                if (result != null && result.compareTo(sb.toString()) < 0) return; // 사전순으로 더 빠른 결과가 이미 저장되어 있으면 중단
            }

            minCost = totalCost; // 최소 비용 갱신
            result = sb.toString(); // 결과 저장
            return;
        }

        // foodIdx번째 식재료 선택
        selectedFood.add(foodIdx);
        for (int i = 0; i < 4; i++) {
            total[i] += foodInfo[foodIdx][i]; // 선택된 식재료의 영양소 합 구하기
        }
        powerset(foodIdx + 1, totalCost + foodInfo[foodIdx][4], total); // 현재 식재료 선택하고 다음 식재료로 넘어감

        // foodIdx번째 식재료 선택 안 함
        selectedFood.remove(selectedFood.size() - 1); // 마지막으로 선택된 식재료 제거
        for (int i = 0; i < 4; i++) {
            total[i] -= foodInfo[foodIdx][i]; // 선택된 식재료의 영양소 합 갱신 (선택하지 않는 경우)
        }
        powerset(foodIdx + 1, totalCost, total); // 현재 식재료를 선택하지 않고 다음 식재료로 넘어감
    }
}