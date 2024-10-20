package programmers.고득점kit.완전탐색;

class 최소직사각형 {
    public int solution(int[][] sizes) {
        int answer = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MIN_VALUE;

        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i][0] > sizes[i][1]) {
                max = Math.max(max, sizes[i][0]);
                min = Math.max(min, sizes[i][1]);
            } else {
                max = Math.max(max, sizes[i][1]);
                min = Math.max(min, sizes[i][0]);
            }
        }

        answer = max * min;
        return answer;
    }
}