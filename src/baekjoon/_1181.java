package baekjoon;

import java.util.*;

public class _1181 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String voca = sc.next();
            set.add(voca);
        }

        Iterator<String> itr = set.iterator();

        while (itr.hasNext()) {
            list.add(itr.next());
        }

        Comparator<String> cmp = (a, b) -> {
            if (a.length() == b.length())
                return a.compareTo(b);
            else
                return a.length() - b.length();
        };

        Collections.sort(list, cmp);

        StringBuilder sb = new StringBuilder();

        for (String s: list) {
            sb.append(s + '\n');
        }

        System.out.print(sb);

    }

}
