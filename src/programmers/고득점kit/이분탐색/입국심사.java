package programmers.고득점kit.이분탐색;

class 입국심사 {
    public long solution(int n, int[] times) {
        long maxTime = 1_000_000_000 * 1_000_000_000L;

        return binarySearch(0L, maxTime, times, n);
    }

    private long binarySearch(long lo, long hi, int[] times, int n) {

        while (lo <= hi) {
            long mid = (lo + hi) / 2;

            if (getCount(times, mid) >= n) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }

        }

        return lo;
    }

    private long getCount(int[] times, long num) {
        long count = 0;

        for (int time: times) {
            count += (long) num / time;
        }

        return count;
    }
}