package programmers.고득점kit.그래프;

/**
 * 위상 정렬 같은 경우에는 순위를 구하거나, 커리큘럼 같은 문제에 맞지,
 * 순위를 구할 수 있는 학생이 몇명이냐, 이런 거는 안맞음. 이러한 문제는 floyd가 적절.
 */
class 순위 {

    private boolean[][] map;
    public int solution(int n, int[][] results) {
        map = new boolean[n + 1][n + 1];

        for (int i = 0; i < results.length; i++) {
            int x = results[i][0];
            int y = results[i][1];

            map[x][y] = true;
        }

        floyd(n);

        int count;
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            count = 0;

            for (int j = 1; j <= n; j++) {
                if (map[i][j] || map[j][i]) {
                    count++;
                }
            }

            if (count == n - 1) {
                answer++;
            }
        }

        return answer;
    }

    private void floyd(int n) {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (i == k)
                    continue;
                for (int j = 1; j <= n; j++) {
                    if (map[i][k] && map[k][j])
                        map[i][j] = true;
                }
            }
        }
    }
}