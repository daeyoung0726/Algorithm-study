package programmers;

// Integer.parseInt() 를 통해 10진수로 변환 (2 ~ 9들)
// 주어진 결과들에서 가능한 값 모두 찾기
// 구하는 식에서의 각자리 값들이 위에서 구한 가능한 진수보다 크거나 같다면 그 진수 빼버리기.
// 다 끝나면 각 값 계산 (여러개의 결과가 나오면 ?, 한개의 값이면 그 값)

import java.util.*;

public class 수식_복원하기 {
    private LinkedHashSet<String> set;
    private Set<Integer> possible;

    public String[] solution(String[] expressions) {
        set = new LinkedHashSet<>();
        possible = new HashSet<>();

        List<String> temp = new ArrayList<>();
        int len = expressions.length;
        for (int i = 0; i < len; i++) {
            if (expressions[i].charAt(expressions[i].length() - 1) == 'X') {
                set.add(expressions[i].substring(0, expressions[i].length() - 1));
            } else {
                temp.add(expressions[i]);
            }
        }

        checkPossible(temp);
        checkPossibleInQuestion();

        return run();
    }

    // 정답이 있는 값 중 가능한 진수 찾기
    private void checkPossible(List<String> temp) {
        StringTokenizer str;

        int[] count = new int[10];

        for (String s : temp) {
            boolean plus = s.contains("+");
            str = new StringTokenizer(s, "+-= ");
            String x = str.nextToken();
            String y = str.nextToken();
            String z = str.nextToken();

            for (int i = 2; i < 10; i++) {
                try {
                    int intX = Integer.parseInt(x, i);
                    int intY = Integer.parseInt(y, i);
                    int intZ = Integer.parseInt(z, i);
                    if (plus) {
                        if (intX + intY == intZ)
                            count[i]++;
                    } else {
                        if (intX - intY == intZ)
                            count[i]++;
                    }
                } catch (NumberFormatException e) {}
            }
        }

        for (int i = 2; i < 10; i++) {
            if (count[i] == temp.size())
                possible.add(i);
        }
    }

    // 가능한 진수 중, 질문에 대한 문제에서 불가능한 값 진수 추려내기 (진수보다 같거나 큰값)
    private void checkPossibleInQuestion() {
        StringTokenizer str;

        for (String s : set) {
            str = new StringTokenizer(s, "+-= ");

            while (str.hasMoreTokens()) {
                String ss = str.nextToken();
                for (int i = 0; i < ss.length(); i++) {
                    int x = ss.charAt(i) - '0';

                    for (int j = 2; j <= x; j++) {
                        possible.remove(j);
                    }
                }
            }
        }
    }

    // 정답 구하기
    private String[] run() {
        StringTokenizer str;

        String[] answer = new String[set.size()];
        int idx = 0;
        for (String s : set) {
            boolean plus = s.contains("+");
            str = new StringTokenizer(s, "+-= ");
            String x = str.nextToken();
            String y = str.nextToken();

            List<Integer> list = new ArrayList<>();

            for (int num : possible) {
                int intX = Integer.parseInt(x, num);
                int intY = Integer.parseInt(y, num);

                if (plus) {
                    if (!list.contains(Integer.valueOf(Integer.toString(intX + intY, num))))
                        list.add(Integer.valueOf(Integer.toString(intX + intY, num)));
                } else {
                    if (!list.contains(Integer.valueOf(Integer.toString(intX - intY, num))))
                        list.add(Integer.valueOf(Integer.toString(intX - intY, num)));
                }
            }

            if (list.size() > 1)
                answer[idx++] = s + "?";
            else
                answer[idx++] = s + list.get(0);
        }

        return answer;
    }
}