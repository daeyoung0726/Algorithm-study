package programmers.고득점kit.스택_큐;

import java.util.*;

class 주식가격 {
    public int[] solution(int[] prices) {
        Stack<Point> stack = new Stack<>();

        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            if (stack.isEmpty()) {
                stack.push(new Point(i, prices[i]));
            }

            while (true) {
                if (!stack.isEmpty() && stack.peek().price > prices[i]) {
                    Point p = stack.pop();
                    answer[p.idx] = i - p.idx;
                } else if (!stack.isEmpty() && i == prices.length - 1) {    // 마지막
                    Point p = stack.pop();
                    answer[p.idx] = i - p.idx;
                } else {
                    stack.push(new Point(i, prices[i]));
                    break;
                }
            }
        }

        return answer;
    }
}

class Point {
    int idx;
    int price;

    Point(int idx, int price) {
        this.idx = idx;
        this.price = price;
    }
}