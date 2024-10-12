package programmers.고득점kit.그리디;

import java.util.*;

class 단속카메라 {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> a[0] - b[0]);

        int answer = 1;
        int lastCamera = routes[0][1];

        for (int i = 1; i < routes.length; i++) {
            if (lastCamera < routes[i][0]) {
                answer++;
                lastCamera = routes[i][1];
            } else
                lastCamera = Math.min(lastCamera, routes[i][1]);
        }

        return answer;
    }
}