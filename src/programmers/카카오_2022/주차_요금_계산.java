package programmers.카카오_2022;

import java.util.*;

public class 주차_요금_계산 {
    public int[] solution(int[] fees, String[] records) {

        Map<Integer, Integer> recode = new HashMap<>();
        Map<Integer, Integer> cumTime = new HashMap<>();

        for (String r: records) {
            String[] split = r.split(" ");

            int time = calcTime(split[0]);
            int num = Integer.parseInt(split[1]);
            String state = split[2];

            if (state.equals("IN")) {
                recode.put(num, time);
            } else {
                int inTime = recode.get(num);
                int outTime = time - inTime;

                if (cumTime.containsKey(num)) {
                    cumTime.put(num, cumTime.get(num) + outTime);
                } else {
                    cumTime.put(num, outTime);
                }
                recode.remove(num);
            }
        }

        int fullTime = 23 * 60 + 59;
        for (int n : recode.keySet()) {
            int remainTime = recode.get(n);
            int result = fullTime - remainTime;
            if (cumTime.containsKey(n)) {
                cumTime.put(n, cumTime.get(n) + result);
            } else {
                cumTime.put(n, result);
            }
        }

        List<int[]> list = new ArrayList<>();

        for (int n : cumTime.keySet()) {
            list.add(new int[] {n, cumTime.get(n)});
        }

        Collections.sort(list, (a, b) -> (a[0] - b[0]));

        int[] answer = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            int x = list.get(i)[1];

            int sum = fees[1];
            x -= fees[0];
            int y = 0;
            if (x > 0) {
                y += x / fees[2];
                if (x % fees[2] != 0)
                    y++;
            }
            sum += y * fees[3];
            answer[i] = sum;
        }
        return answer;
    }

    private int calcTime(String time) {
        String[] split = time.split(":");

        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}