package programmers.고득점kit.완전탐색;

import java.util.*;

class 모의고사 {
    public int[] solution(int[] answers) {
        int[] std1 = { 1, 2, 3, 4, 5 };
        int[] std2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
        int[] std3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };

        int[] correctNum = new int[3];

        int std1Idx = -1;
        int std2Idx = -1;
        int std3Idx = -1;

        for (int i = 0; i < answers.length; i++) {
            std1Idx = (std1Idx + 1) % std1.length;
            std2Idx = (std2Idx + 1) % std2.length;
            std3Idx = (std3Idx + 1) % std3.length;

            if (answers[i] == std1[std1Idx]) {
                correctNum[0]++;
            }
            if (answers[i] == std2[std2Idx]) {
                correctNum[1]++;
            }
            if (answers[i] == std3[std3Idx]) {
                correctNum[2]++;
            }
        }

        int max = 0;
        for (int val: correctNum) {
            max = Math.max(max, val);
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < correctNum.length; i++) {
            if (max == correctNum[i])
                list.add(i+1);
        }

        int[] answer = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}

