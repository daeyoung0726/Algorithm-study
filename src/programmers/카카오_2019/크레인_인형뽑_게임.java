package programmers.카카오_2019;

// stack 사용 peek을 사용 후 만약 같다면 꺼내기. 아니면 현재 값도 넣기

import java.util.Stack;

public class 크레인_인형뽑_게임 {
    public int solution(int[][] board, int[] moves) {

        Stack<Integer> stack = new Stack<>();

        int answer = 0;
        for (int i = 0; i < moves.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int num = board[j][moves[i] - 1];
                if (num != 0) {
                    if (stack.isEmpty())
                        stack.push(num);
                    else {
                        if (stack.peek() == num) {
                            answer += 2;
                            stack.pop();
                        } else
                            stack.push(num);
                    }

                    board[j][moves[i] - 1] = 0;
                    break;
                }
            }
        }
        return answer;
    }
}