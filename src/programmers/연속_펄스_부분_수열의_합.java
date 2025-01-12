package programmers;

public class 연속_펄스_부분_수열의_합 {
    public long solution(int[] sequence) {
        long max1 = Long.MIN_VALUE;
        long max2 = Long.MIN_VALUE;

        long current1 = 0;
        long current2 = 0;

        for (int i = 0; i < sequence.length; i++) {
            int value = (i % 2 == 0) ? sequence[i] : -1 * sequence[i];

            current1 = Math.max(current1 + value, value);
            max1 = Math.max(max1, current1);

            current2 = Math.max(current2 + (value * -1), value * -1);
            max2 = Math.max(max2, current2);
        }

        return Math.max(max1, max2);
    }
}