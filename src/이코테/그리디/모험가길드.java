package 이코테.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 모험가길드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Integer> lists = new ArrayList<>();

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            lists.add(Integer.parseInt(str.nextToken()));
        }

        Collections.sort(lists, Collections.reverseOrder());

        int result = 0;
        int size = n;
        for (int i = 0; i < n; i++) {
            int x = lists.get(i);

            if (x <= size+1) {
                result++;
                size -= x;
            }

            if (size == 0)
                break;
        }

        System.out.println(result);
    }
}
