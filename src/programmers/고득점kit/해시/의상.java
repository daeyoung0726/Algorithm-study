package programmers.고득점kit.해시;

import java.util.*;

class 의상 {
    public int solution(String[][] clothes) {
        Map<String, Integer> hm = new HashMap<>();

        // 옷을 종류별로 개수 세기
        for (int i = 0; i < clothes.length; i++) {
            if (hm.containsKey(clothes[i][1]))
                hm.put(clothes[i][1], hm.get(clothes[i][1]) + 1);
            else
                hm.put(clothes[i][1], 1);
        }

        int answer = 1;

        // (각 카테고리의 옷 수 + 1) 을 모두 곱함 1을 더해주는 이유는 안입는 옷도 포함하기 위해.
        for (int value : hm.values()) {
            answer *= (value + 1);
        }

        // 아무것도 입지 않는 경우를 1 빼줌
        return answer - 1;
    }
}
