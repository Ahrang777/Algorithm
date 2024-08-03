import java.io.*;
import java.util.*;

public class Main {
    static int N, min;
    static int[] people;
    static List<Integer>[] graph;
    static boolean[] isSelected;
    static List<Integer> aList, bList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        people = new int[N + 1];
        graph = new List[N + 1];
        isSelected = new boolean[N + 1];
        min = Integer.MAX_VALUE;

        aList = new ArrayList<>();
        bList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());

            for (int j = 0; j < M; j++) {
                int e = Integer.parseInt(st.nextToken());
                graph[i].add(e);
            }
        }

        for (int i = 1; i <= N / 2; i++) {
            combination(0, 0, i);
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void combination(int start, int cnt, int limit) {
        if (cnt == limit) {
            aList.clear();
            bList.clear();

            for (int i = 1; i <= N; i++) {
                if (isSelected[i]) {
                    aList.add(i);
                } else {
                    bList.add(i);
                }
            }

            if (aList.size() == 0 || aList.size() == N) {
                return;
            }

            if (isValid(aList) && isValid(bList)) {
                min = Math.min(min, calculate(aList, bList));
            }

            return;
        }

        for (int i = start; i <= N; i++) {
            isSelected[i] = true;
            combination(i + 1, cnt + 1, limit);
            isSelected[i] = false;
        }
    }

    private static boolean isValid(List<Integer> list) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        q.offer(list.get(0));
        visited[list.get(0)] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph[now]) {
                if (!list.contains(next) || visited[next]) {
                    continue;
                }

                visited[next] = true;
                q.offer(next);
            }
        }

        for (int n : list) {
            if (!visited[n]) {
                return false;
            }
        }
        return true;
    }

    private static int calculate(List<Integer> aList, List<Integer> bList) {
        int totalA = 0;
        int totalB = 0;

        for (int a : aList) {
            totalA += people[a];
        }

        for (int b : bList) {
            totalB += people[b];
        }

        return Math.abs(totalA - totalB);
    }
}