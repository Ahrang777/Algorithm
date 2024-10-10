import java.util.*;
class Solution {
    public int solution(int N, int number) {
        Set<Integer>[] set = new Set[9];
        int tmp = N;
        for(int i = 1; i < 9; i++) {
            set[i] = new HashSet<Integer>();
            set[i].add(tmp);
            tmp = tmp*10 + N;
        }
        for(int i = 1; i < 9; i++) {
            for(int j = 1;j < i; j++ ) {
                for(Integer x : set[j]) {
                    for(Integer y : set[i-j]) {
                        set[i].add(x+y);
                        set[i].add(x-y);
                        set[i].add(y-x);
                        set[i].add(x*y);
                        if(x != 0) set[i].add(y/x);
                        if(y != 0) set[i].add(x/y);
                    }
                }
            }
        }
        for(int i = 1; i < 9; i++) {
            if(set[i].contains(number)) {
                return i;
            }
        }
        return -1;
    }
}