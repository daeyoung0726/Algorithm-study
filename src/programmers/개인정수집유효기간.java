package programmers;

import java.util.*;

public class 개인정수집유효기간 {
    public List<Integer> solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        // 1. 오늘 날짜를 일수 기준으로 모두 더함
        String[] td = today.split("[.]");
        int privacyDate = (Integer.parseInt(td[0])*28*12)+(Integer.parseInt(td[1])*28)+Integer.parseInt(td[2]);

        // 2. map으로 개인정보 기준을 셋팅
        for(int i=0; i<terms.length; i++){
            String[] tm = terms[i].split(" ");
            map.put(tm[0], Integer.parseInt(tm[1]));
        }

        // 3. 각각 개인정보 날짜와 비교
        for(int i=0; i<privacies.length; i++){
            String[] pr = privacies[i].split(" ");
            String term = pr[1];

            String[] prDate = pr[0].split("[.]");
            int endDate = (Integer.parseInt(prDate[0])*12*28) + (Integer.parseInt(prDate[1])*28) +
                    (Integer.parseInt(prDate[2])) + (map.get(term)*28);

            if(privacyDate >= endDate){
                answer.add(i+1);
            }
        }

        return answer;
    }
}
