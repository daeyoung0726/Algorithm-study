package baekjoon.DFS_BFS;

// dfs. 존재하는 알파벳을 확인. 각 값에 9부터 시적해서 브루트포스

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1339 {
    private static String[] arr;
    private static Map<Character, Integer> map;
    private static List<Character> list;
    private static boolean[] visited;

    private static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        arr = new String[n];
        map = new HashMap<>();
        list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            arr[i] = s;
            for (int j = 0; j < s.length(); j++) {
                if (!list.contains(s.charAt(j))) {
                    list.add(s.charAt(j));
                }
            }
        }
        visited = new boolean[list.size()];

        dfs(list.size(), 0, 9);

        System.out.println(max);
    }

    private static void dfs(int n, int depth, int score) {
        if (n == depth) {
            int sum = calc();
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                map.put(list.get(i), score);
                dfs(n, depth + 1, score - 1);
                visited[i] = false;
            }
        }
    }

    private static int calc() {
        int sum = 0;
        for (String s : arr) {
            int num = 0;
            for (int i = 0; i < s.length(); i++) {
                num = num * 10 + map.get(s.charAt(i));
            }

            sum += num;
        }

        return sum;
    }
}