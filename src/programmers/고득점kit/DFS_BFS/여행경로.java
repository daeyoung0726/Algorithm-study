package programmers.고득점kit.DFS_BFS;

class 여행경로 {

    private boolean[] visited;
    private String[] s;
    public String[] solution(String[][] tickets) {
        int len = tickets.length;
        visited = new boolean[len];
        s = new String[len+1];

        dfs(len, 0, 0, new String[len+1], tickets);
        return s;
    }

    private void dfs(int len, int depth, int pre, String[] seq, String[][] tickets) {

        if (len == depth) {
            if (s[0] == null) {
                for (int i = 0; i <= len; i++) {
                    s[i] = seq[i];
                }
                return;
            }

            for (int i = 0; i <= len; i++) {
                if (s[i].compareTo(seq[i]) == 0)
                    continue;
                if (s[i].compareTo(seq[i]) < 0) {
                    break;
                }
                if (s[i].compareTo(seq[i]) > 0) {
                    for (int j = 0; j <= len; j++) {
                        s[j] = seq[j];
                    }
                    break;
                }
            }
            return;
        }

        for (int i = 0; i < len; i++) {
            if (depth == 0) {
                if (tickets[i][0].equals("ICN")) {
                    visited[i] = true;
                    seq[depth] = tickets[i][0];
                    dfs(len, depth + 1, i, seq, tickets);
                    visited[i] = false;
                }
            } else {
                if (!visited[i] && tickets[pre][1].equals(tickets[i][0])) {
                    visited[i] = true;
                    seq[depth] = tickets[i][0];

                    if (depth + 1 == len)
                        seq[depth+1] = tickets[i][1];

                    dfs(len, depth + 1, i, seq, tickets);
                    visited[i] = false;
                }
            }
        }
    }
}