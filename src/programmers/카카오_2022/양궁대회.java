package programmers.카카오_2022;

// bfs이용. idx대로 이동하여 어피치의 idx보다 1 크게 값 넣기 + 0으로 넣기
// 끝까지 도달하면 점수 계산. 점수가 큰걸 저장. 만약, 점수 같다면 가장 낮은 점수의 화살을 가장 많이 쏜 곳
// 화살이 남는다면, 딴 점수 중 가장 낮은 점수에 몰빵

public class 양궁대회 {
    private int[] temp;
    private int[] answer;
    private int[] copyInfo;
    private int max = 0;

    public int[] solution(int n, int[] info) {
        copyInfo = info;
        temp = new int[11];
        answer = new int[11];
        dfs(n, 0, 10);
        if (max == 0)
            return new int[] {-1};
        return answer;
    }

    private void dfs(int n, int depth, int len) {
        if (n == 0 || depth - 1 == len) {
            int[] score = sumScore(len);
            int appeachScore = score[0];
            int lionScore = score[1];

            if (n != 0) {
                temp[10] += n;
            }

            int result = lionScore - appeachScore;
            if (max < result) {
                max = result;
                createAnswer(len);
            } else if (max == result) {
                if (compareScore(len))
                    createAnswer(len);
            }
            // 다시 초기화
            temp[10] = 0;
            return;
        }

        int num = copyInfo[depth];
        // 점수 획득
        temp[depth] = num + 1;
        if (num + 1 <= n)
            dfs(n - (num + 1), depth + 1, len);

        // 점수 포기
        temp[depth] = 0;
        dfs(n, depth + 1, len);
    }

    private int[] sumScore(int len) {
        int appeachScore = 0;
        int lionScore = 0;
        for (int i = 0; i <= len; i++) {
            if (copyInfo[i] != 0 || temp[i] != 0) {
                if (copyInfo[i] < temp[i])
                    lionScore += 10 - i;
                else
                    appeachScore += 10 - i;
            }
        }

        return new int[] {appeachScore, lionScore};
    }

    private boolean compareScore(int len) {

        for (int i = len; i >= 0; i--) {
            if (answer[i] < temp[i]) {
                return true;
            }
            if (answer[i] > temp[i])
                return false;
        }
        return false;
    }

    private void createAnswer(int len) {
        for (int i = 0; i <= len; i++)
            answer[i] = temp[i];
    }
}