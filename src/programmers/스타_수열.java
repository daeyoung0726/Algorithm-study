package programmers;

public class 스타_수열 {
    public int solution(int[] a) {

        int[] cum = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            cum[a[i]]++;
        }

        int answer = 0;
        for (int i = 0; i < cum.length; i++) {
            if (cum[i] <= answer)   // 현재 최대 길이보다 숫자가 적다면
                continue;

            int cnt = 0;
            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] != a[j + 1] && (a[j] == i || a[j + 1] == i)) {
                    cnt++;
                    j++;    // 2개 짝짓는거기에
                }
            }

            answer = Math.max(answer, cnt);
        }
        return answer * 2;
    }
}