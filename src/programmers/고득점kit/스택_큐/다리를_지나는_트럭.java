package programmers.고득점kit.스택_큐;

import java.util.*;

class 다리를_지나는_트럭 {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        List<Truck> list = new ArrayList<>();

        int currentWeight = 0;
        for (int truck: truck_weights) {
            if (currentWeight + truck > weight) {
                while (currentWeight + truck > weight) {
                    Truck firstTruck = list.remove(0);
                    answer += firstTruck.l;
                    currentWeight -= firstTruck.w;

                    for (Truck t: list) {
                        t.l -= firstTruck.l;
                    }
                }
            } else {

                for (Truck t: list) {
                    t.l -= 1;
                    if (t.l == 0) {
                        currentWeight -= t.w;
                    }
                }

                while (true) {  // for-each 문에서는 값에 요소에 대한 변경(삭제)하면 안됨. 그래서, 따로 제거함.
                    if (!list.isEmpty() && list.get(0).l == 0)
                        list.remove(0);
                    else
                        break;
                }
                answer++;
            }

            list.add(new Truck(truck, bridge_length));
            currentWeight += truck;
        }

        if (!list.isEmpty()) {
            int size = list.size();
            Truck lastTruck = list.get(size - 1);
            answer += lastTruck.l;
        }

        return answer;
    }
}

class Truck {
    int w;  // 트럭 무게
    int l;  // 남은 길이

    Truck(int w, int l) {
        this.w = w;
        this.l = l;
    }
}