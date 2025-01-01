package programmers.카카오_2023;

// 10진수 2진수로 변환
// 2진수가 짝수이면 맨 앞에 0 붙이기
// 중앙 값을 기준으로 좌측, 우측 나누기 (dfs)
// 중앙값은 루트 값. 만약 루트가 0이라면 못만드는 것 (0반환) - 길이가 1이라면 넘기기

public class 표현_가능한_이진트리 {

    private int[] answer;
    private int idx = 0;
    private boolean check;

    public int[] solution(long[] numbers) {

        answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            check = true;
            String s = Long.toString(numbers[i], 2);

            int j = 1;
            int sLen = s.length();
            while (true) {
                int len = (int) (Math.pow(2, j) - 1);

                if (sLen <= len)
                    break;

                j++;
            }

            int l = (int) (Math.pow(2, j) - 1);
            if (sLen < l) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < l - sLen; k++) {
                    sb.append("0");
                }

                s = sb.toString() + s;
            }

            if (s.equals("0")) {
                answer[idx++] = 0;
                continue;
            }

            if (s.equals("1")) {
                answer[idx++] = 1;
                continue;
            }

            dfs(s);

            if (check) {
                answer[idx++] = 1;
            } else {
                answer[idx++] = 0;
            }
        }
        return answer;
    }

    private void dfs(String s) {

        if (!check)
            return;

        int mid = s.length() / 2;
        int rootNum = s.charAt(mid) - '0';

        if (rootNum == 0 && (s.contains("1"))) {
            check = false;
            return;
        }

        if (s.length() > 3) {
            dfs(s.substring(0, mid));
            dfs(s.substring(mid + 1));
        }
    }
}