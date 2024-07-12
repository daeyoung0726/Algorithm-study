package 이코테.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 원소교체 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(str.nextToken());
        int K = Integer.parseInt(str.nextToken());

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();


        str = new StringTokenizer(br.readLine(), " ");
        for (int j = 0; j < N; j++) {
            list1.add(Integer.parseInt(str.nextToken()));
        }


        str = new StringTokenizer(br.readLine(), " ");
        for (int j = 0; j < N; j++) {
            list2.add(Integer.parseInt(str.nextToken()));
        }


        Collections.sort(list1);
        Collections.sort(list2, Collections.reverseOrder());

        for (int i = 0; i < K; i++) {
            int x = list1.remove(0);
            int y = list2.remove(0);
            list1.add(y);
            list2.add(x);
        }

        int sum = 0;
        for (int a: list1) {
            sum += a;
        }

        System.out.println(sum);
    }
}
