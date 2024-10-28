package programmers.카카오_2018;

// 단어 나눠서 list에 각각 저장
// 따로 idx를 두고, 같으면 같이 움직이기. 다르다면, 값 비교를 통해 작은 값을 움직이기
// 한 쪽이 다 움직이면, 남는 쪽은 다 합집합 쪽에 넣기

import java.util.*;

public class 뉴스_클러스터링 {

    private ArrayList<String> list1;
    private ArrayList<String> list2;

    public int solution(String str1, String str2) {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        divideStr(str1, str2);

        if (list1.isEmpty() && list2.isEmpty()) {
            return 65536;
        }
        Collections.sort(list1);
        Collections.sort(list2);


        return result(list1.size(), list2.size());
    }

    private void divideStr(String str1, String str2) {

        String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < str1.length() - 1; i++) {
            if (s.contains(str1.charAt(i) + "") && s.contains(str1.charAt(i + 1) + "")) {
                String temp = (str1.charAt(i) + "" + str1.charAt(i + 1)).toLowerCase();
                list1.add(temp);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            if (s.contains(str2.charAt(i) + "") && s.contains(str2.charAt(i + 1) + "")) {
                String temp = (str2.charAt(i) + "" + str2.charAt(i + 1)).toLowerCase();
                list2.add(temp);
            }
        }
    }

    private int result(int size1, int size2) {
        int intersection = 0;
        int union = 0;

        int a = 0, b = 0;

        while (a < size1 && b < size2) {
            String x = list1.get(a);
            String y = list2.get(b);
            if (x.equals(y)) {
                intersection++;
                a++;
                b++;
            } else {
                if (x.compareTo(y) < 0) {
                    a++;
                } else {
                    b++;
                }
            }
            union++;
        }

        if (a < size1) {
            union += (size1 - a);
        }

        if (b < size2) {
            union += (size2 - b);
        }

        double divide = (double) intersection / union;

        return (int) (divide * 65536);

    }
}