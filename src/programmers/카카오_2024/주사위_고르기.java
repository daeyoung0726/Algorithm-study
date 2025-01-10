package programmers.카카오_2024;

// 주사위 나뉘는 모든 경우의 수 구하기
// HashMap에 값에 대한 개수 넣기
// keySet을 통해 list에 담기. 담은 후 내림차순으로 정렬
// 상대방 주사위 또한 값을 구하고 그 값으로 개수 구하기

import java.util.*;

public class 주사위_고르기 {
    private boolean[] isA;
    private List<int[]> list;
    private int[][] copyDice;

    private int max = Integer.MIN_VALUE;
    private int[] answer;

    public int[] solution(int[][] dice) {

        isA = new boolean[dice.length];
        list = new ArrayList<>();
        copyDice = dice;
        answer = new int[dice.length / 2];

        dfs(0, dice.length, 0);
        return answer;
    }

    private void dfs(int depth, int n, int x) {
        if (depth == n / 2) {
            int result = calc(); // 값 구하기 수행

            if (result > max) {
                int j = 0;
                for (int i = 0; i < n; i++) {
                    if (isA[i])
                        answer[j++] = i + 1;
                }
                max = result;
            }
            return;
        }

        for (int i = x; i < n; i++) {
            isA[i] = true;
            dfs(depth + 1, n, i + 1);
            isA[i] = false;
        }
    }

    private int calc() {
        List<int[]> diceA = new ArrayList<>();
        List<int[]> diceB = new ArrayList<>();

        // A와 B의 주사위를 분리
        for (int i = 0; i < copyDice.length; i++) {
            if (isA[i]) {
                diceA.add(copyDice[i]);
            } else {
                diceB.add(copyDice[i]);
            }
        }

        // A의 모든 경우의 합산 점수 탐색
        List<Integer> sumsA = new ArrayList<>();
        for (int[] a : diceA) {
            if (sumsA.isEmpty()) {
                for (int num : a) {
                    sumsA.add(num);
                }
            } else {
                List<Integer> newSums = new ArrayList<>();
                for (int sum : sumsA) {
                    for (int num : a) {
                        newSums.add(sum + num);
                    }
                }
                sumsA = newSums;
            }
        }

        // B의 모든 경우의 합산 점수 탐색
        List<Integer> sumsB = new ArrayList<>();
        for (int[] b : diceB) {
            if (sumsB.isEmpty()) {
                for (int num : b) {
                    sumsB.add(num);
                }
            } else {
                List<Integer> newSums = new ArrayList<>();
                for (int sum : sumsB) {
                    for (int num : b) {
                        newSums.add(sum + num);
                    }
                }
                sumsB = newSums;
            }
        }

        Collections.sort(sumsA, (a, b) -> b - a);
        Collections.sort(sumsB, (a, b) -> b - a);

        int winCount = 0;

        int start = 0;
        for (int i = 0; i < sumsB.size(); i++) {
            for (int j = start; j < sumsA.size(); j++) {
                if (sumsA.get(j) <= sumsB.get(i)) {
                    break;
                }
                start++;
            }
            winCount += start;
        }

        return winCount;
    }

}