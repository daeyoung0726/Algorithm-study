package programmers;

public class 택배_상자_꺼내기 {
    public int solution(int n, int w, int num) {
        int layer = (num - 1) / w;
        int temp = (num - 1) % w;

        int column;
        if (layer % 2 == 0) {
            column = temp;
        } else {
            column = w - 1 - temp;
        }

        int count = 1;

        // 왼 -> 오 (2 * (w - column) - 1 - 짝
        // 오 -> 왼 (2 * (column + 1)) - 1 - 홀
        for (int i = layer; i <= ((n - 1) / w); i++) {
            if (i % 2 == 0) {
                num += 2 * (w - column) - 1;
            } else {
                num += (2 * (column + 1)) - 1;
            }

            if (num <= n)
                count++;
            else
                break;
        }

        return count;
    }
}

/**
 * import java.util.*;
 *
 * class Solution {
 *     public int solution(int n, int w, int num) {
 *         int answer = 0;
 *         List<List<Integer>> list = new ArrayList<>();
 *
 *         for (int i = 0; i < w; i++) {
 *             list.add(new ArrayList<>());
 *         }
 *
 *         int type = 1;
 *         int x = 0;
 *         int idx = 0;
 *         for (int i = 1; i <= n; i++) {
 *             if (i == num) {
 *                 idx = x;
 *             }
 *             list.get(x).add(i);
 *             x += type;
 *             if (x < 0) {
 *                 x++;
 *                 type *= -1;
 *             } else if (x >= w) {
 *                 x--;
 *                 type *= -1;
 *             }
 *         }
 *
 *         for (int i = 0; i < list.get(idx).size(); i++) {
 *             if (list.get(idx).get(i) == num) {
 *                 answer = list.get(idx).size() - i;
 *             }
 *         }
 *         return answer;
 *     }
 * }
 */