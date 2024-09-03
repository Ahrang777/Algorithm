class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int bonus = 0;
        int time = h1 * 3600 + m1 * 60 + s1;
        if (time == 0 || time == 43200) {
            bonus = 1;
        }
        
        return count(h2, m2, s2) - count(h1, m1, s1) + bonus;
    }
    
    private int count(int h, int m, int s) {
        int time = h * 3600 + m * 60 + s;
        
        int mCount = time * 59 / 3600;
        int hCount = time * 719 / 43200;
        int count = mCount + hCount;
        
        if (h >= 12) {
            count--;
        }
        
        return count;
    }
}