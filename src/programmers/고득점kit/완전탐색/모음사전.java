package programmers.고득점kit.완전탐색;

class 모음사전 {

    private boolean isFinished;
    private String w;
    private final String[] s = {"A", "E", "I", "O", "U"};
    private int count = 0;
    private int result = 0;

    public int solution(String word) {
        isFinished = false;
        w = word;

        dfs("", 0);
        return result;
    }

    private void dfs(String word, int depth) {
        if (isFinished) {
            return;
        }

        if (depth == 5) {
            if (word.equals(w)) {
                isFinished = true;
                result = count;
            }
            return;
        }

        if (word.equals(w)) {
            isFinished = true;
            result = count;
            return;
        }

        for (int i = 0; i < 5; i++) {
            String temp = word + s[i];
            count++;
            dfs(temp, depth + 1);
        }
    }
}