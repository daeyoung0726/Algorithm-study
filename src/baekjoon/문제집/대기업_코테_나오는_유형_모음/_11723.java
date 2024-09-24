package baekjoon.문제집.대기업_코테_나오는_유형_모음;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        MySet mySet = new MySet();

        StringBuilder sb = new StringBuilder();
        StringTokenizer str;
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");

            String call = str.nextToken();
            int num = 0;
            if (str.hasMoreTokens())
                num = Integer.parseInt(str.nextToken());

            switch (call) {
                case "add": mySet.add(num);
                    break;

                case "remove": mySet.remove(num);
                    break;

                case "check": sb.append(mySet.check(num)).append('\n');
                    break;

                case "toggle": mySet.toggle(num);
                    break;

                case "all": mySet.all();
                    break;

                case "empty": mySet.empty();
                    break;
            }
        }

        System.out.println(sb.toString());
    }
}

class MySet {
    private int[] set;

    MySet() {
        this.set = new int[21];
    }

    public void add(int idx) {
        set[idx - 1] = 1;
    }

    public void remove(int idx) {
        set[idx - 1] = 0;
    }

    public int check(int idx) {
        return set[idx - 1];
    }

    public void toggle(int idx) {
        set[idx - 1] = (set[idx - 1] == 0) ? 1 : 0;
    }

    public void all() {
        for (int i = 0; i < 20; i++) {
            set[i] = 1;
        }
    }

    public void empty() {
        for (int i = 0; i < 20; i++) {
            set[i] = 0;
        }
    }
}