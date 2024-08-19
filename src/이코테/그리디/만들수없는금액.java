package 이코테.그리디;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class 만들수없는금액 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        Collections.sort(list);

        int target = 1;

        for (int x: list) {
            if (target < x)
                break;
            target += x;
        }

        System.out.println(target);
    }
}
