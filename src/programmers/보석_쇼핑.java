package programmers;

// Set, Map 사용.
// Set은 들어있는 종류 확인용. Map은 종류별 개수 (범위 줄이고 늘리며 종류 넣고 빼기 위함)

import java.util.*;

public class 보석_쇼핑 {
    public int[] solution(String[] gems) {
        Set<String> gemSet = new HashSet<>();
        Set<String> nowGem = new HashSet<>();
        Map<String, Integer> gemMap = new HashMap<>();

        for (int i = 0; i < gems.length; i++) {
            gemSet.add(gems[i]);
            if (!gemMap.containsKey(gems[i]))
                gemMap.put(gems[i], 0);
        }

        int start = 0;
        int end = 0;

        int x = 0;
        int y = Integer.MAX_VALUE;
        while (end < gems.length) {
            nowGem.add(gems[end]);
            gemMap.put(gems[end], gemMap.get(gems[end]) + 1);

            if (gemSet.size() == nowGem.size()) {
                if (end - start < y - x) {
                    x = start;
                    y = end;
                }

                while (gemSet.size() == nowGem.size()) {
                    gemMap.put(gems[start], gemMap.get(gems[start]) - 1);
                    if (gemMap.get(gems[start]) == 0) {
                        nowGem.remove(gems[start]);
                        if (end - start < y - x) {
                            x = start;
                            y = end;
                        }
                    }
                    start++;
                }
            }

            end++;
        }

        return new int[] {x + 1, y + 1};
    }
}