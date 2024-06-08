package homework;

import java.util.Scanner;

public class HW8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String ipAddr = sc.next();

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < 4; i++) {
            for (int j = i+1; j < i+4; j++) {
                if (ipAddr.substring(j).length() > 6)
                    continue;
                for (int k = j+1; k < j+4; k++) {

                    if (k < ipAddr.length()) {
                        String part1 = ipAddr.substring(0, i);
                        String part2 = ipAddr.substring(i, j);
                        String part3 = ipAddr.substring(j, k);
                        String part4 = ipAddr.substring(k);

                        if (checkRange(part1) && checkRange(part2) && checkRange(part3) && checkRange(part4)) {
                            sb.append(part1 + "." + part2 + "." + part3 + "." + part4).append('\n');
                        }
                    }
                }
            }
        }

        System.out.println(sb);
    }

    private static boolean checkRange(String part) {
        try {
            int num = Integer.parseInt(part);
            return num >= 0 && num <= 255;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
