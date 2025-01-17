package programmers;

public class 혼자서_하는_틱택토 {
    public int solution(String[] board) {
        char[][] newBoard = new char[3][3];
        for (int i = 0; i < 3; i++) {
            newBoard[i] = board[i].toCharArray();
        }

        int oCount = 0, xCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (newBoard[i][j] == 'O') oCount++;
                if (newBoard[i][j] == 'X') xCount++;
            }
        }

        if (oCount < xCount || oCount - xCount > 1) {
            return 0;
        }

        boolean oWin = isWinner(newBoard, 'O');
        boolean xWin = isWinner(newBoard, 'X');

        if (oWin && xWin) {
            return 0;
        }

        // "O"가 승리했을 때, "O"의 개수가 "X"보다 1 많아야 함
        if (oWin && oCount != xCount + 1) {
            return 0;
        }

        // "X"가 승리했을 때, "O"의 개수와 "X"의 개수가 같아야 함
        if (xWin && oCount != xCount) {
            return 0;
        }

        // 모든 조건 만족
        return 1;
    }

    private boolean isWinner(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }
}
