package programmers;

public class 다트게임 {
    public int solution(String dartResult) {
        int answer = 0;
        String number = "0123456789";

        int[] score = new int[3];
        int size = dartResult.length();

        int index = -1;

        for (int i = 0; i < size; i++) {
            char c = dartResult.charAt(i);

            if (number.contains(String.valueOf(c))) {
                index++;
                if (c == '1' && i < size - 1 && dartResult.charAt(i+1) == '0') {
                    score[index] = 10;
                    i++;
                } else {
                    score[index] = c - '0';
                }

            } else if (c == 'S') {
                score[index] = (int) Math.pow(score[index], 1);
            } else if (c == 'D') {
                score[index] = (int) Math.pow(score[index], 2);
            } else if (c == 'T') {
                score[index] = (int) Math.pow(score[index], 3);
            } else if (c == '*') {
                if (index > 0)
                    score[index-1] *= 2;
                score[index] *= 2;
            } else if (c == '#') {
                score[index] *= -1;
            }

        }

        for (int s: score) {
            answer += s;
        }
        return answer;
    }

}
