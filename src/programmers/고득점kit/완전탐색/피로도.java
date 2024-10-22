package programmers.고득점kit.완전탐색;

class 피로도 {

    private int max = Integer.MIN_VALUE;
    private boolean[] visited;
    private int[][] d;
    private int len;

    public int solution(int k, int[][] dungeons) {
        len = dungeons.length;
        visited = new boolean[len];
        d = dungeons;

        dfs(k, 0, 0);
        return max;
    }

    private void dfs(int k, int count, int idx) {
        if (idx == len) {
            max = Math.max(max, count);
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (k >= d[i][0]) {
                    dfs(k - d[i][1], count + 1, idx + 1);
                } else {
                    dfs(k, count, idx + 1);
                }
                visited[i] = false;
            }
        }
    }
}