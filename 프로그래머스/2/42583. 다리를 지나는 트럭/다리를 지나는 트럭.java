import java.util.*;

class Solution {
    static class Truck {
        int pos, weight;
        
        public Truck(int pos, int weight) {
            this.pos = pos;
            this.weight = weight;
        }
        
        public void move() {
            this.pos++;
        }                
    }
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int curWeight = 0;
        
        Queue<Truck> waitQ = new ArrayDeque<>();
        Queue<Truck> moveQ = new ArrayDeque<>();
        
        for (int truck_weight : truck_weights) {
            waitQ.offer(new Truck(0, truck_weight));
        }
        
        while (!waitQ.isEmpty() || !moveQ.isEmpty()) {   
            answer++;
            
            // 다리가 비어있는 경우
            if (moveQ.isEmpty()) {
                Truck truck = waitQ.poll();
                moveQ.offer(truck);
                truck.move();
                curWeight += truck.weight;
                continue;
            }
            
            // 다리위에 있는 트럭 하나씩 이동
            for (Truck truck : moveQ) {
                truck.move();
            }
            
            // 제일 앞 트럭이 다리를 건넌 경우
            if (moveQ.peek().pos > bridge_length) {
                Truck truck = moveQ.poll();
                curWeight -= truck.weight;
            }
            
            // 트럭이 추가로 다리에 올라가는 경우
            if (!waitQ.isEmpty() && waitQ.peek().weight + curWeight <= weight) {
                Truck truck = waitQ.poll();
                moveQ.offer(truck);
                truck.move();
                curWeight += truck.weight;
            }
        }
        
        return answer;
    }
}