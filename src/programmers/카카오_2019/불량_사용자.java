package programmers.카카오_2019;

// 완전탐색.
// banned_id 하나에 대해 찾기. 찾으면 boolean처리 -> 다음 banned_id
// 다 끝나면 그 다음 번째의 banned_id
// Set에 user_id를 '_'으로 구분하여 넣고 공통된 값이 있다면 무시

import java.util.*;

public class 불량_사용자 {
    private Set<String> set;
    private String[] user_id;
    private String[] banned_id;
    private boolean[] visited;

    public int solution(String[] user_id, String[] banned_id) {
        set = new HashSet<>();
        this.user_id = user_id;
        this.banned_id = banned_id;

        visited = new boolean[user_id.length];
        dfs(0, banned_id.length);
        return set.size();
    }

    private void dfs(int depth, int n) {
        if (depth == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < user_id.length; i++) {
                if (visited[i]) {
                    sb.append(user_id[i] + "_");
                }
            }
            String result = sb.toString();

            set.add(result);
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (!visited[i] && checkId(i, depth)) {
                visited[i] = true;
                dfs(depth + 1, n);
                visited[i] = false;
            }
        }
    }

    private boolean checkId(int x, int y) {
        String userId = user_id[x];
        String bannedId = banned_id[y];

        if (userId.length() != bannedId.length())
            return false;

        for (int i = 0; i < userId.length(); i++) {
            if (bannedId.charAt(i) == '*')
                continue;
            if (userId.charAt(i) != bannedId.charAt(i))
                return false;
        }
        return true;
    }
}