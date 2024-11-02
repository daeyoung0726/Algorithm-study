package programmers.카카오_2021;

import java.util.*;

public class 순위_검색 {

    private Map<String, List<Integer>> map;
    public int[] solution(String[] info, String[] query) {

        map = new HashMap<>();

        for (String s: info) {
            String[] split = s.split(" ");
            insertInfo(0, "", split);
        }

        for (List<Integer> scores : map.values()) {
            Collections.sort(scores);
        }

        int[] answer = new int[query.length];
        int i = 0;

        for (String q: query) {
            String[] newQuery = q.replaceAll(" and ", "").split(" ");
            answer[i++] = findResult(newQuery);
        }

        return answer;
    }

    private void insertInfo(int depth, String s, String[] split) {

        if (depth == 4) {
            if (!map.containsKey(s)) {
                map.put(s, new ArrayList<>());
            }
            map.get(s).add(Integer.parseInt(split[4]));

            return;
        }

        String temp = s;
        insertInfo(depth + 1, temp + "-", split);
        insertInfo(depth + 1, temp + split[depth], split);
    }

    private int findResult(String[] query) {
        if (!map.containsKey(query[0]))
            return 0;

        List<Integer> scores = map.get(query[0]);

        int lo = 0;
        int hi = scores.size() - 1;

        int queryScore = Integer.parseInt(query[1]);
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            int score = scores.get(mid);

            if (score >= queryScore)
                hi = mid - 1;
            else
                lo = mid + 1;
        }

        return scores.size() - lo;
    }
}