package programmers.카카오_2022;

// dfs를 통해서 탐색
// 양, 늑대 개수 세가며 이동하고, 만약 잡아먹히면 뒤로 이동
// 이동할 떄 마다 visited 작성. 양, 늑대 셀때마다 visited는 초기화.


// 테스트 7, 9, 12, 16틀림
import java.util.*;

public class 양과_늑대 {
    private ArrayList<ArrayList<Integer>> graphs;
    private boolean[] visited;
    private int[] copyInfo;

    private int sheepCount;
    private int answer = 1;
    private boolean isMax = false;

    public int solution(int[] info, int[][] edges) {
        graphs = new ArrayList<>();

        int len = info.length;

        for (int i = 0; i < len; i++) {
            graphs.add(new ArrayList<>());
        }
        visited = new boolean[len];
        copyInfo = info;

        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];

            graphs.get(x).add(y);
            graphs.get(y).add(x);
        }

        sheepCount = 0;
        for (int i = 0; i < info.length; i++) {
            if (info[i] == 0)
                sheepCount++;
        }

        copyInfo[0] = 2;
        dfs(0, 1, 0);
        return answer;
    }

    private void dfs(int idx, int sheep, int wolf) {
        if (isMax)
            return;

        visited[idx] = true;

        for (int x : graphs.get(idx)) {
            if (!visited[x]) {
                if (copyInfo[x] == 0) {
                    copyInfo[x] = 2;
                    visited = new boolean[copyInfo.length];
                    answer = Math.max(answer, sheep + 1);

                    if (answer == sheepCount) {
                        isMax = true;
                        return;
                    }

                    dfs(x, sheep + 1, wolf);
                    copyInfo[x] = 0;
                } else if (copyInfo[x] == 1 && sheep > wolf + 1) {
                    copyInfo[x] = 2;
                    dfs(x, sheep, wolf + 1);
                    copyInfo[x] = 1;
                } else if (copyInfo[x] == 2) {
                    dfs(x, sheep, wolf);
                }
            }
        }
    }
}