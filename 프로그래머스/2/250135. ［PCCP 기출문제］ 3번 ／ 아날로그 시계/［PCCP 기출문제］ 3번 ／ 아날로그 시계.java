// 초침 = 1초에 1씩
// 분침 = 1초에 1/60
// 시침 = 1초에 5/3600 = 1/720

// 초침 = 720m/s
// 분침 = 12m/s
// 시침 = 1m/s

// 총 원형 운동장의 길이가 43200m

// 시 분 초가 만나는 점은 12:00:00, 0:00:00이 유일하다.

// 이 상황을 제외하고, 초침이 분침을 초월, 초침이 시침을 초월, 또는 일치할때마다 알람을 울리게 하면 된다.

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