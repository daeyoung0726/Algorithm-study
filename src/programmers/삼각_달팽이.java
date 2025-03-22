package programmers;

public class 삼각_달팽이 {
    public int[] solution(int n) {
        int[][] arr = new int[n][n];

        int x = 0;
        int y = 0;
        int num = 1;
        arr[0][0] = num++;

        while (true) {
            boolean check = false;

            while (x + 1 < n && arr[x + 1][y] == 0) {
                arr[++x][y] = num++;
                check = true;
            }

            if (!check)
                break;

            while (y + 1 < n && arr[x][y + 1] == 0) {
                arr[x][++y] = num++;
            }

            while (x - 1 >= 0 && y - 1 >= 0 && arr[x - 1][y - 1] == 0) {
                arr[--x][--y] = num++;
            }

        }

        int[] answer = new int[num - 1];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != 0) {
                    answer[idx++] = arr[i][j];
                }
            }
        }

        return answer;
    }
}