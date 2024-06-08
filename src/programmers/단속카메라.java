package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class 단속카메라 {
    public int solution(int[][] routes) {
        int answer = 1;

        Comparator<int[]> cmp = (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            else
                return a[0] - b[0];
        };

        Arrays.sort(routes, cmp);

        int lastPosition = routes[0][1];

        for (int i = 1; i < routes.length; i++) {
            if (routes[i][0] > lastPosition) {
                answer++;
                lastPosition = routes[i][1];
            } else {
                lastPosition = Math.min(lastPosition, routes[i][1]);
            }
        }


        return answer;
    }

}
