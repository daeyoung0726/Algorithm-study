package programmers;

import java.util.*;

public class 봉인된_주문 {
    public String solution(long n, String[] bans) {
        int[] arr = new int[11];

        Comparator<String> cmp = (a, b) -> {
            if (a.length() == b.length())
                return a.compareTo(b);
            return a.length() - b.length();
        };

        Arrays.sort(bans, cmp);
        Arrays.fill(arr, 30);

        List<Long> list = new ArrayList<>();

        for (String s : bans) {
            long sum = 0;
            for (int i = 0; i < s.length(); i++) {
                sum = sum * 26 + (s.charAt(i) - 'a' + 1);
            }
            list.add(sum);
        }

        int newN = binarySearch(n, list);
        int ii = 0;
        for (int i = 1; i <= newN; i++) {
            if (list.size() <= newN + ii) {
                n++; continue;
            }
            if (++n == list.get(newN + ii)) {
                i--;
                ii++;
            }
        }

        return convertToAlphabet(n);
    }

    private int binarySearch(long num, List<Long> list) {
        int lo = 0;
        int hi = list.size() - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (list.get(mid) == num)
                return mid + 1;

            if (list.get(mid) < num)
                lo = mid + 1;
            else
                hi = mid - 1;
        }

        return lo;
    }

    private String convertToAlphabet(long n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            n--;
            sb.append((char) ('a' + (n % 26)));
            n /= 26;
        }

        return sb.reverse().toString();
    }
}