package programmers.고득점kit.해시;

import java.util.Map;
import java.util.HashMap;

class 완주하지_못한_선수 {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < participant.length; i++) {
            if (hm.containsKey(participant[i]))
                hm.put(participant[i], hm.get(participant[i]) + 1);
            else
                hm.put(participant[i], 1);
        }

        for (int i = 0; i < completion.length; i++) {
            hm.put(completion[i], hm.get(completion[i]) - 1);
        }

        String answer = "";

        for (String name: hm.keySet()) {
            if (hm.get(name) != 0) {
                answer = name;
                break;
            }
        }

        return answer;
    }
}