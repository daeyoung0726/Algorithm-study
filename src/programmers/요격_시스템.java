package programmers;

// 왼쪽 값을 기준으로 정렬. 같다면 오른쪽 짧은거
// 공통된 범위에 속하는지 계속 구하기. 범위도 공통된 곳으로 업데이트

import java.util.*;

public class 요격_시스템 {
    public int solution(int[][] targets) {
        Comparator<int[]> cmp = (a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        };

        Arrays.sort(targets, cmp);

        return run(targets);
    }

    private int run(int[][] targets) {
        int count = 1;
        int left = targets[0][0];
        int right = targets[0][1];

        for (int i = 1; i < targets.length; i++) {
            if (targets[i][0] < right) {
                left = Math.max(left, targets[i][0]);
                right = Math.min(right, targets[i][1]);
            } else {
                left = targets[i][0];
                right = targets[i][1];
                count++;
            }
        }

        return count;
    }
}