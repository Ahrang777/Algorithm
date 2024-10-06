import java.util.*;
class Solution {
    static class Car implements Comparable<Car> {
        String number;
        int fee;
        
        public Car(String number, int fee) {
            this.number = number;
            this.fee = fee;
        }
        
        @Override
        public int compareTo(Car o) {
            return this.number.compareTo(o.number);
        }
    }
    
    // fees >> 기본 시간(분), 기본 요금(원), 단위 시간(분), 단위 요금(원)
    // records >> 시각 차량번호 내역
    public int[] solution(int[] fees, String[] records) {
        List<Car> cars = new ArrayList<>();
        
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        // 입차 기록 : 차량번호, 시각
        Map<String, String> entrance = new HashMap<>();
        
        // 차량번호, 누적 주차 시간
        Map<String, Integer> results = new HashMap<>();
        
        for (String record : records) {
            // 시각, 차량번호, 내역
            String[] arr = record.split(" ");
            String time = arr[0];
            String car = arr[1];
            
            // 입차
            if (entrance.get(car) == null) {
                entrance.put(car, time);
            } else {    // 출차
                String inTime = entrance.get(car);
                String[] in = entrance.get(car).split(":");
                String[] out = time.split(":");
                
                int inHour = Integer.parseInt(in[0]);
                int inMin = Integer.parseInt(in[1]);
                int outHour = Integer.parseInt(out[0]);
                int outMin = Integer.parseInt(out[1]);
                
                // 총 주차시간
                int total = (outHour - 1 - inHour) * 60 + 60 + outMin - inMin;
                int fee = 0;
                
                results.put(car, (results.getOrDefault(car, 0) + total));
                entrance.remove(car);
            }
        }
        
        
        // fees >> 기본 시간(분), 기본 요금(원), 단위 시간(분), 단위 요금(원)
        // 23:59에 나가는 차
        for (Map.Entry<String, String> entry : entrance.entrySet()) {
            String car = entry.getKey();
            String time = entry.getValue();
            String[] in = time.split(":");
            int inHour = Integer.parseInt(in[0]);
            int inMin = Integer.parseInt(in[1]);
            
            // 총 주차시간
            int total = (23 - inHour) * 60 + 59 - inMin;
            int fee = 0;
            
            results.put(car, (results.getOrDefault(car, 0) + total));
        }
        
        // 주차요금 계산
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            String car = entry.getKey();
            int total = entry.getValue();
            int fee = 0;
            
            if (total <= baseTime) {
                fee = baseFee;
            } else {
                total -= baseTime;
                fee += baseFee;
                
                if (total % unitTime == 0) {
                    fee += (total / unitTime) * unitFee;
                } else {
                    fee += (total / unitTime) * unitFee + unitFee;
                }
            }
            cars.add(new Car(car, fee));
        }
        
        Collections.sort(cars);
        int[] answer = new int[cars.size()];
        for (int i = 0; i < cars.size(); i++) {
            answer[i] = cars.get(i).fee;
        }
        
        return answer;
    }
}