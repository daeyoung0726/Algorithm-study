package programmers;

public class 올바른_괄호의_개수 {
    private int answer = 0;

    public int solution(int n) {
        dfs(0, 0, n);
        return answer;
    }

    private void dfs(int a, int b, int n) {
        if (a == n && b == n) {
            answer++;
            return;
        }

        if (a != n) {
            dfs(a + 1, b, n);
        }

        if (a <= b)
            return;

        if (b != n) {
            dfs(a, b + 1, n);
        }
    }
}