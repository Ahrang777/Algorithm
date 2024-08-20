import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int type, atk, hp;

        public Node(int type, int atk, int hp) {
            this.type = type;
            this.atk = atk;
            this.hp = hp;
        }
    }
    static int N, A;
    static Node[] rooms;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());

        rooms = new Node[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int atk = Integer.parseInt(st.nextToken());
            int hp = Integer.parseInt(st.nextToken());

            rooms[i] = new Node(type, atk, hp);
        }

        long s = 1;
        long e = Long.MAX_VALUE - 1;
        long answer = e;

        while (s <= e) {
            // 최대 HP
            long mid = (s + e) / 2;

            if (isValid(mid, A)) {
                e = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                s = mid + 1;
            }
        }

        System.out.println(answer);
    }

    /**
     *
     * @param maxHp    최대 HP
     * @param atk   공격력
     * @return 해당 조건으로 N번 방까지 클리어 가능여부
     */
    private static boolean isValid(long maxHp, long atk) {
        long curHp = maxHp;

        for (Node r : rooms) {
            if (r.type == 1) {  // 몬스터
                int mHp = r.hp;
                int mAtk = r.atk;

                // 용사의 첫 공격에 몬스터가 죽는 경우 생각
                // 용사는 공격받을 필요가 없다.
                // 따라서 몬스터가 용사를 공격한 횟수 = 용사가 몬스터를 공격한 횟수 - 1이 된다.
                if (mHp % atk == 0) {
                    curHp -= mAtk * (mHp / atk - 1);
                } else {
                    curHp -= mAtk * (mHp / atk);
                }

                if (curHp <= 0) {
                    return false;
                }
            } else {    // 포션
                atk += r.atk;
                curHp = curHp + r.hp > maxHp ? maxHp : curHp + r.hp;
            }
        }

        return true;
    }
}