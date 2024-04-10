package baekjoon;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _14425 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> map = new HashMap<>();

        int count = 0;

        int n = sc.nextInt();
        int m = sc.nextInt();

        for(int i = 0; i < n; i++) {
            String text = sc.next();
            map.put(text, 1);
        }

        for(int j = 0; j < m; j++) {
            String check = sc.next();
            if(map.get(check) != null)
                count++;
        }

        System.out.println(count);
    }
}
