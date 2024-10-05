package programmers.정렬;

import java.util.*;

public class 실패율 {
    public int[] solution(int N, int[] stages) {

        int[] failedClear = new int[N];
        int[] reachStage = new int[N];

        for (int i = 0; i < stages.length; i++) {
            if (stages[i] <= N)
                failedClear[stages[i] - 1]++;
            for (int j = 0; j < stages[i]; j++) {
                if (j < N)
                    reachStage[j]++;
                else
                    break;
            }
        }

        List<Point> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (reachStage[i] == 0) {
                list.add(new Point(0, i+1));
            } else
                list.add(new Point((double) failedClear[i] / reachStage[i], i+1));
        }

        Comparator<Point> cmp = (a, b) -> {
            if (Double.compare(a.result, b.result) == 0)
                return a.idx - b.idx;
            return Double.compare(b.result, a.result);
        };

        Collections.sort(list, cmp);

        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = list.get(i).idx;
        }

        return answer;
    }
}

class Point {
    double result;
    int idx;

    Point(double result, int idx) {
        this.result = result;
        this.idx = idx;
    }
}