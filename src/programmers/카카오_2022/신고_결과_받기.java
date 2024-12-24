package programmers.카카오_2022;

// 자신이 신고한 사람에 대한 정보를 저장해두기. HashMap<String, List<String>> 형식
// 사용자 신고도 HashMap을 통해 횟수를 카운팅.
// 카운팅 하기 전에 이미 신고한 적이 있는지 확인하고 신고.

import java.util.*;

public class 신고_결과_받기 {
    private Map<String, ArrayList<String>> member;
    private Map<String, Integer> reportCount;

    public int[] solution(String[] id_list, String[] report, int k) {

        member = new HashMap<>();
        reportCount = new HashMap<>();

        for (String id: id_list) {
            member.put(id, new ArrayList<>());
        }

        runReport(report);

        return result(id_list, k);
    }

    private void runReport(String[] report) {

        for (String r: report) {
            String[] splitMem = r.split(" ");

            String reporter = splitMem[0];
            String reported = splitMem[1];

            if (!member.get(reporter).contains(reported)) {
                member.get(reporter).add(reported);
                if (!reportCount.containsKey(reported))
                    reportCount.put(reported, 1);
                else
                    reportCount.put(reported, reportCount.get(reported) + 1);
            }
        }
    }

    private int[] result(String[] id_list, int k) {
        Map<String, Integer> memIdx = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            memIdx.put(id_list[i], i);
        }

        int[] answer = new int[id_list.length];

        for (String name: reportCount.keySet()) {
            if (reportCount.get(name) >= k) {
                for (String reporter: id_list) {
                    if (member.get(reporter).contains(name)) {
                        answer[memIdx.get(reporter)]++;
                    }
                }
            }
        }

        return answer;
    }
}