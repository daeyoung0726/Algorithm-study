package 이코테.구현;

public class 문자열압축 {
    public int solution(String s) {

        int len = s.length();
        int min = s.length();

        StringBuilder sb;

        for (int i = 1; i <= len / 2; i++) {
            sb = new StringBuilder();

            String sub1 = s.substring(0, i);

            int count = 1;

            for (int j = i; j < len; j += i) {
                String sub2;
                if (j + i >= len)
                    sub2 = s.substring(j);
                else
                    sub2 = s.substring(j, j+i);

                if (sub1.equals(sub2))
                    count++;
                else {
                    if (count != 1)
                        sb.append(count + sub1);
                    else
                        sb.append(sub1);
                    count = 1;
                }

                sub1 = sub2;
            }
            if (count != 1)
                sb.append(count + sub1);
            else
                sb.append(sub1);
            if (sb.length() != 0)
                min = Math.min(min, sb.length());
        }
        return min;
    }
}
