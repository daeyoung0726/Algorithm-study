package programmers.고득점kit.완전탐색;

import java.util.*;

class 카펫 {
    public int[] solution(int brown, int yellow) {

        List<int[]> list = new ArrayList<>();
        int x = 3, y = 3;
        boolean isInit = false;
        while (true) {

            if (isInit && (x-2) * (y-2) > yellow)
                break;

            if ((x-2) * (y-2) < yellow) {
                y++;
                if (isInit)
                    isInit = false;
            } else {
                if ((x-2) * (y-2) == yellow) {
                    list.add(new int[] {x, y});
                }
                x++;
                y = 3;
                isInit = true;
            }
        }

        int[] answer = new int[2];
        for (int i = 0; i < list.size(); i++) {
            int[] now = list.get(i);
            int nx = now[0];
            int ny = now[1];

            if (2*nx + 2*ny - 4 == brown) {
                answer[0] = ny;
                answer[1] = nx;
                break;
            }
        }

        return answer;
    }
}