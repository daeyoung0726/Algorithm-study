package programmers.카카오_2021;

// 브루트포스로 경우의 수 다 구하기.
// course의 값에 맞는 경우가 나온다면, orders를 돌면서 2개 이상인 것 확인. (2개 이상이라면 바로 끝)
// 다 끝나면 정렬해서 반환

import java.util.*;

public class 메뉴_리뉴얼 {

    private String[] copyOrdes;
    private ArrayList<Candidate> candi;
    private int preCount;

    public String[] solution(String[] orders, int[] course) {

        copyOrdes = orders;

        List<String> lists = new ArrayList<>();
        for (int c: course) {
            preCount = 0;
            candi = new ArrayList<>();
            dfs(c, 0, "");
            for (Candidate candidate: candi) {
                if (candidate.count == preCount) {
                    lists.add(candidate.word);
                }
            }
        }

        Collections.sort(lists);

        String[] answer = new String[lists.size()];
        for (int i = 0; i < lists.size(); i++) {
            answer[i] = lists.get(i);
        }
        return answer;
    }

    private void dfs(int n, int x, String s) {
        if (s.length() == n) {

            int count = 0;
            boolean check;
            for (String str: copyOrdes) {
                check = true;
                for (int i = 0; i < s.length(); i++) {
                    if (!str.contains(String.valueOf(s.charAt(i)))) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    count++;
                }
            }

            if (count >= 2) {
                candi.add(new Candidate(s, count));
            }

            if (count > preCount) {
                preCount = count;
            }
            return;
        }

        for (int i = x; i < 26; i++) {
            String temp = s;
            temp += (char) (i + 'A');
            dfs(n, i + 1, temp);
        }
    }
}

class Candidate {
    String word;
    int count;

    Candidate(String word, int count) {
        this.word = word;
        this.count = count;
    }
}