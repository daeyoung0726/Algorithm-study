package programmers;

public class _2_이하로_다른_비트 {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        int idx = 0;
        for (long number : numbers) {
            answer[idx++] = run(number);
        }
        return answer;
    }

    private long run(long number) {
        String num1 = Long.toString(number, 2);

        int idx = -1;
        for (int i = num1.length() - 1; i >= 0; i--) {
            if (num1.charAt(i) == '0') {
                idx = i;
                break;
            }
        }

        if (idx == -1) {
            String temp = "10" + num1.substring(1);
            return Long.parseLong(temp, 2);
        } else {
            if (idx == num1.length() - 1) {
                String temp = num1.substring(0, num1.length() - 1) + "1";
                return Long.parseLong(temp, 2);
            } else {
                String temp = num1.substring(0, idx) + "10" + num1.substring(idx + 2);
                return Long.parseLong(temp, 2);
            }
        }
    }
}