package programmers.카카오_2018;

// :에 대해서 split해서 이차원배열에 값을 넣기. +1씩 하는것.
// 앞에서 부터 계산.
// 시간대 별로 배차 간격인 t만큼 추가하면서 이미 온 사람에 대해서 체크, 배차 간격에서 첫 크루 시간 기록
// 맨 뒤 배차부터 보고, 맨 뒤에 차 탈 수 있으면, 맨 뒤 배차 시간
// 만약 맨 뒤 배차도 못탄다면, 맨 뒤 배차 첫번째 크루의 시간보다 1 빠르게

import java.util.*;

public class 셔틀버스 {
    public String solution(int n, int t, int m, String[] timetable) {
        int[][] waitingTime = new int[24][60];

        int size = 0;   // 대기열 사람들 다 태웠는지 확인하기 위해.
        for (int i = 0; i < timetable.length; i++) {
            String[] arr = timetable[i].split(":");
            int hour = Integer.parseInt(arr[0]);
            int minute = Integer.parseInt(arr[1]);

            waitingTime[hour][minute]++;
            size++;
        }

        int[] bus = new int[n];  // 버스 순서, 탑승 인원
        Map<Integer, int[]> lastEnter = new HashMap<>();

        int HH = 9;
        int MM = 0;
        int x = 0;
        boolean isFull;
        for (int k = 0; k < n; k++) {
            int enter = 0;
            isFull = false;
            if (size == 0)
                break;

            for (int i = x; i <= HH; i++) {
                for (int j = 0; j < 60; j++) {
                    if (i == HH && j > MM) {
                        continue;
                    }
                    if (waitingTime[i][j] != 0) {

                        while (waitingTime[i][j] != 0) {
                            enter++;
                            waitingTime[i][j]--;
                            size--;
                            if (enter == m) {
                                isFull = true;

                                lastEnter.put(k, new int[] {i, j});
                                break;
                            }
                        }
                        if (isFull) {
                            x = i;
                            break;
                        }
                    }
                }
                if (isFull) {
                    break;
                }
            }
            bus[k] = enter;

            MM += t;

            if (MM >= 60) {
                MM -= 60;
                HH++;
            }
        }

        int resultH = 9;
        int resultM = 0;

        if (bus[n - 1] < m) {
            resultM = (n - 1) * t;

            resultH += resultM / 60;
            resultM %= 60;
        } else {

            int[] last = lastEnter.get(n - 1);
            resultH = last[0];
            resultM = last[1] - 1;

            if (resultM < 0) {

                resultH--;
                resultM = 59;
            }
        }

        String a = "";
        String b = "";
        if (resultH < 10) {
            a = "0" + resultH;
        } else {
            a = String.valueOf(resultH);
        }

        if (resultM < 10) {
            b = "0" + resultM;
        } else {
            b = String.valueOf(resultM);
        }

        return a + ":" + b;
    }
}