package programmers;

public class 가장_긴_팰린드롬 {
    public int solution(String s) {
        int answer = 1;

        for (int i = 0; i < s.length() - 1; i++) {
            int temp1 = run(i, i, s);
            int temp2 = run(i, i + 1, s);
            int temp = Math.max(temp1, temp2);
            answer = Math.max(answer, temp);
        }

        return answer;
    }

    private int run(int left, int right, String s) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }

            left--;
            right++;
        }

        return right - left - 1;    // left, right는 이미 증감연산자로 넘어간 후임.
    }
}