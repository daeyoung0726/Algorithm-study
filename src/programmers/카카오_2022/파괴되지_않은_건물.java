package programmers.카카오_2022;

public class 파괴되지_않은_건물 {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;

        int[][] diff = new int[n + 1][m + 1];   // +1 만큼 한 이유는 마지막 행, 열에 대한 값도 처리하기 위해

        for (int[] s : skill) {
            int type = s[0], r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4], degree = s[5];
            int effect = (type == 1) ? -degree : degree;

            diff[r1][c1] += effect;
            diff[r1][c2 + 1] -= effect; // 끝부분은 다시 0으로 처리하기 위해
            diff[r2 + 1][c1] -= effect; // 행의 끝부분을 다시 0으로 처리하기 위해
            diff[r2 + 1][c2 + 1] += effect; // 끝 행에 대해서 - 했기에 끝행 끝 열은 + 해줘야함
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }

        for (int j = 0; j < m; j++) {
            for (int i = 1; i < n; i++) {
                diff[i][j] += diff[i - 1][j];
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] += diff[i][j];
                if (board[i][j] > 0) count++;
            }
        }

        return count;
    }
}

