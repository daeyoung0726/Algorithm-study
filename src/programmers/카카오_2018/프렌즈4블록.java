package programmers.카카오_2018;

// 처음부터 돌면서 오, 아래, 오 대각선 아래 검사.
// 만약에 4개 맞다면, list에 넣기. 그리고, 끝나면 list에서 값 꺼내면서 해당 위치 0으로 초기화
// 각 열 맨 아래에서 검사하며 내리기

import java.util.*;

public class 프렌즈4블록 {

    private char[][] map;
    private ArrayList<int[]> lists;

    public int solution(int m, int n, String[] board) {

        map = new char[m][n];
        lists = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String s = board[i];
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        boolean isRun;
        while (true) {
            isRun = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
            System.out.println();
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (map[i][j] != '0' && isFour(i, j)) {
                        isRun = true;
                    }
                }
            }

            if (isRun) {
                for (int[] now: lists) {
                    map[now[0]][now[1]] = '0';
                }

                fallBlock(m, n);
                lists.clear();
            } else {
                break;
            }
        }


        int answer = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == '0')
                    answer++;
            }
        }

        return answer;
    }

    private boolean isFour(int x, int y) {

        if (map[x][y] == map[x + 1][y] &&
                map[x][y] == map[x][y + 1] &&
                map[x][y] == map[x + 1][y + 1]) {

            if (!lists.contains(map[x][y])) {
                lists.add(new int[] {x, y});
            }
            if (!lists.contains(map[x + 1][y])) {
                lists.add(new int[] {x + 1, y});
            }
            if (!lists.contains(map[x][y + 1])) {
                lists.add(new int[] {x, y + 1});
            }
            if (!lists.contains(map[x + 1][y + 1])) {
                lists.add(new int[] {x + 1, y + 1});
            }
            return true;
        }
        return false;
    }

    private void fallBlock(int m, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = m - 1; j > 0; j--) {
                if (map[j][i] == '0') {
                    char temp = '0';
                    for (int k = j - 1; k >= 0; k--) {
                        if (map[k][i] != '0') {
                            temp = map[k][i];
                            map[k][i] = '0';
                            break;
                        }
                    }
                    map[j][i] = temp;
                }
            }
        }
    }
}