import java.io.*;
import java.util.*;

public class Main {
    // 윷놀이 판을 연결 리스트 구조에 담는다.
    static class Node {
        int score;  // 윷놀이판에서 해당 칸의 점수
        boolean isEmpty; // 해당 칸이 비었는지
        boolean isFinish;   // 해당 칸이 도착 지점인지

        Node next;  // 연결된 다음 칸 >> 빨강 방향
        Node fast;  // 연결된 파랑방향 칸 >> 10, 20, 30 은 이 부분이 존재한다.

        public Node(int score) {
            this.score = score;
            this.isEmpty = true;
        }

        // 노드 연결
        public Node addNext(int score) {
            Node nextNode = new Node(score);
            this.next = nextNode;
            return nextNode;
        }

        // 노드 찾기 (지름길 놓는 지점 10, 20, 30 을 찾기 위한 함수)
        public static Node getNode(Node start, int score) {
            Node tmp = start;

            while (true) {
                if (tmp == null) return null;
                if (tmp.score == score) return tmp;
                tmp = tmp.next;
            }
        }
    }

    // 4개의 말
    public static Node[] horese = new Node[4];

    // 주사위 눈, 각 주사위 눈만큼 움직일 말의 번호
    public static int[] dice, order;

    // 결과로 출력할 최댓값
    public static int max = Integer.MIN_VALUE;

    // 시작 지점
    public static Node start;

    // 게임판 초기화
    public static void init() {
        start = new Node(0);

        // start 유지하기 위해 다른 참조변수 이용
        Node tmp = start;

        // 외각 경로
        for (int i = 2; i <= 40; i += 2) {
            tmp = tmp.addNext(i);
        }

        // 도착 지점
        Node end = tmp.addNext(0);
        end.isFinish = true;
        end.next = end; // NPE 방지하기 위해 null할당x

        // 가운데 교차점
        Node cross = new Node(25);

        // 25 -> 30 -> 35 -> 40
        tmp = cross.addNext(30);
        tmp = tmp.addNext(35);
        tmp.next = Node.getNode(start, 40);

        // 10 -> 13 -> 16 -> 19 -> 25
        tmp = Node.getNode(start, 10);
        tmp = tmp.fast = new Node(13);
        tmp = tmp.addNext(16);
        tmp = tmp.addNext(19);
        tmp.next = cross;

        // 20 -> 22 -> 4 -> 25
        tmp = Node.getNode(start, 20);
        tmp = tmp.fast = new Node(22);
        tmp = tmp.addNext(24);
        tmp.next = cross;

        // 30 -> 28 -> 27 -> 26 -> 25
        tmp = Node.getNode(start, 30);
        tmp = tmp.fast = new Node(28);
        tmp = tmp.addNext(27);
        tmp = tmp.addNext(26);
        tmp.next = cross;
    }

    public static int playGame() {
        Arrays.fill(horese, start);

        int score = 0;

        for (int i = 0; i < 10; i++) {
            Node now = horese[order[i]];
            now.isEmpty = true; // 이동을 하니까 현재 칸 빈칸 처리

            // 주사위 숫자 만큼 이동
            for (int j = 0; j < dice[i]; j++) {
                // 움직일때 처음 시작지점이 파란칸
                if (j == 0 && now.fast != null) {
                    // 파란 방향으로 이동
                    now = now.fast;
                } else {
                    // 빨간 방향으로 이동
                    now = now.next;
                }
            }

            // 이동한 지점으로 말에 반영
            horese[order[i]] = now;

            // 이동을 마친 칸에 다른 말이 있는 경우
            // 해당 말은 고를 수 없다.
            if (!now.isEmpty && !now.isFinish) {
                score = 0;
                break;
            } else {
                now.isEmpty = false;
                score += now.score;
            }
        }   // 게임 종료

        // 다음 게임을 위해 현재 말들의 위치를 지워준다.
        for (int i = 0; i < 4; i++) {
            horese[i].isEmpty = true;
        }

        return score;
    }

    public static void dfs(int cnt) {
        if (cnt == 10) {
            max = Math.max(max, playGame());
            return;
        }

        for (int i = 0; i < 4; i++) {
            order[cnt] = i;
            dfs(cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(bf.readLine());

        int result = 0;
        dice = new int[10];
        order = new int[10];
        horese = new Node[4];

        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(stk.nextToken());
        }

        init();

        dfs(0);

        System.out.println(max);
    }
}