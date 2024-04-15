package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14888 {
    private static int[] operator = new int[4];
    private static int[] number;
    private static int MIN = Integer.MAX_VALUE;
    private static int MAX = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        number = new int[n];
        int i = 0;
        while(st.hasMoreTokens()) {
            number[i++] = Integer.parseInt(st.nextToken());
        }

        int j = 0;
        st = new StringTokenizer(br.readLine(), " ");
        while(st.hasMoreTokens()) {
            operator[j++] = Integer.parseInt(st.nextToken());
        }

        calc(number[0], 1);

        System.out.println(MAX);
        System.out.println(MIN);
    }

    private static void calc(int num, int idx) {
        if(number.length == idx) {
            MAX = Math.max(MAX, num);
            MIN = Math.min(MIN, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if(operator[i] != 0) {
                operator[i]--;

                switch(i) {
                    case 0 -> calc(num + number[idx], idx+1);
                    case 1 -> calc(num - number[idx], idx+1);
                    case 2 -> calc(num * number[idx], idx+1);
                    case 3 -> calc(num / number[idx], idx+1);
                }
                operator[i]++;
            }
        }
    }
}
