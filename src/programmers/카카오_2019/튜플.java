package programmers.카카오_2019;

import java.util.*;

public class 튜플 {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2); // 양쪽 끝 { } 모두 지우기
        String[] split = s.split("\\},\\{");    // },{ 기준으로 나누기

        Map<Integer, Integer> hm = new HashMap<>();

        for (String ss: split) {
            String[] splitNum = ss.split(",");
            for (String sss: splitNum) {
                int num = Integer.parseInt(sss);
                if (hm.containsKey(num))
                    hm.put(num, hm.get(num) + 1);
                else
                    hm.put(num, 1);
            }
        }

        List<int[]> list = new ArrayList<>();

        for (int key: hm.keySet()) {
            list.add(new int[] {key, hm.get(key)});
        }

        Collections.sort(list, (a, b) -> b[1] - a[1]);

        int[] answer = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i)[0];
        }
        return answer;
    }
}