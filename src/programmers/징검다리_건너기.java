package programmers;

// 이분탐색. stones의 max를 구한 후, 이분탐색으로 가장 많이 가능한 인원 찾기

public class 징검다리_건너기 {
    public int solution(int[] stones, int k) {
        int hi = 0;
        for (int stone : stones) {
            hi = Math.max(hi, stone);
        }

        return binarySearch(hi, stones, k);
    }

    private int binarySearch(int hi, int[] stones, int k) {
        int lo = 0;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (check(mid, stones, k)) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return lo;
    }

    private boolean check(int mid, int[] stones, int k) {
        int count = 0;

        for (int i = 0; i < stones.length; i++) {
            if (stones[i] <= mid) {
                count++;
            } else {
                count = 0;
            }

            if (count >= k)
                return false;
        }

        return true;
    }
}