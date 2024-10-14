package programmers.고득점kit.그리디;

import java.util.Arrays;

class 체육복 {
    public int solution(int n, int[] lost, int[] reserve) {

        boolean[] check = new boolean[n+1];

        int lostNum = lost.length;
        for (int i = 0; i < lostNum; i++) {
            check[lost[i]] = true;
        }

        int answer = n - lostNum;

        Arrays.sort(reserve);

        for (int i = 0; i < reserve.length; i++) {
            if (check[reserve[i]]) {
                answer++;
                check[reserve[i]] = false;
                reserve[i] = -1;
            }
        }

        boolean r;
        for (int i = 0; i < reserve.length; i++) {
            int reserveIdx = reserve[i];
            r = false;

            if (reserveIdx - 1 > 0 && check[reserveIdx - 1]) {
                answer++;
                check[reserveIdx - 1] = false;
                r = true;
            }

            if (!r && reserveIdx + 1 <= n && check[reserveIdx + 1]) {
                check[reserveIdx + 1] = false;
                answer++;
            }
        }

        return answer;
    }
}