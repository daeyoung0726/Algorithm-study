package programmers;

public class 쿠키_구입 {
    public int solution(int[] cookie) {
        int n = cookie.length;
        int max = 0;

        for (int m = 0; m < n - 1; m++) {
            int left = m;
            int right = m + 1;
            int leftSum = cookie[left];
            int rightSum = cookie[right];

            while (left >= 0 && right < n) {
                if (leftSum == rightSum)
                    max = Math.max(max, leftSum);

                if (leftSum <= rightSum && --left >= 0) {
                    leftSum += cookie[left];
                } else if (leftSum > rightSum && ++right < n) {
                    rightSum += cookie[right];
                }
            }
        }

        return max;
    }
}
