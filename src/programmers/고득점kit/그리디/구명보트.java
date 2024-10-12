package programmers.고득점kit.그리디;

import java.util.Arrays;

class 구명보트 {
    public int solution(int[] people, int limit) {

        Arrays.sort(people);
        int start = 0;
        int end = people.length - 1;
        int answer = 0;

        while (start <= end) {
            if (start == end) {
                answer++;
                end--;
                continue;
            }

            if (people[end] + people[start] <= limit) {
                start++;
            }
            end--;
            answer++;
        }

        return answer;
    }
}