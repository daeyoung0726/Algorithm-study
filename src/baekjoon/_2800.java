package baekjoon;

// 처음부터 탐색.
// '('이면 push하며 index 값도 같이 넣기.
// ')'이면 pop해서 '('뺴고, 다른 곳에 저장 (index)
// 그리고, 차례대로 보며 해당 위치를 '_'으로 바꾸고, 마지막에 replaceAll

import java.util.*;

public class _2800 {
    private static List<int[]> list;
    private static List<String> answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();

        Stack<Integer> stack = new Stack<>();
        list = new ArrayList<>();
        answer = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                list.add(new int[]{stack.pop(), i});
            }
        }

        dfs(s, 0);

        answer.remove(s);

        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();

        for (String a : answer) {
            sb.append(a + '\n');
        }

        System.out.println(sb);
    }

    private static void dfs(String s, int idx) {
        String temp = s.replaceAll("_", "");
        if (!answer.contains(temp)) {
            answer.add(temp);
        }

        for (int i = idx; i < list.size(); i++) {
            int[] now = list.get(i);
            String ss = s.substring(0, now[0]) + "_" +
                    s.substring(now[0] + 1, now[1]) + "_" +
                    s.substring(now[1] + 1);

            dfs(ss, i + 1);
        }
    }
}