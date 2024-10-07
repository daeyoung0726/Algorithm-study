package programmers.고득점kit.DFS_BFS;

class 단어_변환 {

    private int min = Integer.MAX_VALUE;
    private int len;
    private boolean[] visited;


    public int solution(String begin, String target, String[] words) {

        visited = new boolean[words.length];
        len = begin.length();
        dfs(words, begin, target, 0, words.length);

        return (min == Integer.MAX_VALUE) ? 0 : min;
    }

    private void dfs(String[] w, String word, String target, int count, int size) {
        if (word.equals(target)) {
            min = Math.min(min, count);
            return;
        }

        int check;
        for (int i = 0; i < size; i++) {
            check = 0;
            if (!visited[i]) {
                visited[i] = true;

                for (int j = 0; j < len; j++) {
                    if (word.charAt(j) != w[i].charAt(j))
                        check++;
                }

                if (check == 1) {
                    dfs(w, w[i], target, count+1, size);
                }

                visited[i] = false;
            }
        }
    }
}