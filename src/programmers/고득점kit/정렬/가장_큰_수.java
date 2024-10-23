package programmers.고득점kit.정렬;

import java.util.*;

class 가장_큰_수 {
    public String solution(int[] numbers) {
        String[] val = new String[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            val[i] = String.valueOf(numbers[i]);
        }

        Comparator<String> cmp = (a, b) -> {
            return (b + a).compareTo(a + b);
        };

        Arrays.sort(val, cmp);

        if (val[0].equals("0")) {   // 모두 0일 때.
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String v: val) {
            sb.append(v);
        }

        return sb.toString();
    }
}