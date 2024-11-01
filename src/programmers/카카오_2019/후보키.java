package programmers.카카오_2019;

// 모든 경우를 확인해야함. (브루투포스)
// HashSet을 사용할 것. 값을 넣을 땐 string 으로 모두 이어 붙여서 넣기
// 조심해야할 점은, 키의 길이를 지정하여서 1개, 2개 이렇게 가야함.
// 바로 브루투포스 해버리면 1 2 3 4 중 4가 되기 전에 123, 1234 이런 것들이 먼저 되어 제대로 동작 x

import java.util.*;

public class 후보키 {

    private List<Set<Integer>> candi;
    private int[] arr;
    private int count = 0;

    public int solution(String[][] relation) {
        arr = new int[relation[0].length];
        candi = new ArrayList<>();
        for (int i = 1; i <= relation[0].length; i++) {
            dfs(relation, 0, 0, i);
        }
        return count;
    }

    private void dfs(String[][] relation, int depth, int x, int r) {

        if (depth == r) {
            StringBuilder sb = new StringBuilder();
            Set<String> set = new HashSet<>();
            for (int j = 0; j < relation.length; j++) {
                for (int k = 0; k < depth; k++) {
                    sb.append(relation[j][arr[k]] + "_");
                }
                set.add(sb.toString());
                sb.setLength(0);
            }

            if (set.size() == relation.length) {
                Set<Integer> currentSet = new HashSet<>();

                for (int i = 0; i < depth; i++) {
                    currentSet.add(arr[i]);
                }
                if (minimal(currentSet)) {
                    candi.add(currentSet);
                    count++;
                }
                return;
            }
        }

        for (int i = x; i < relation[0].length; i++) {
            arr[depth] = i;
            dfs(relation, depth + 1, i + 1, r);
        }
    }

    private boolean minimal(Set<Integer> currentSet) {
        for (Set<Integer> key : candi) {
            if (currentSet.containsAll(key)) {
                return false;
            }
        }
        return true;

    }
}