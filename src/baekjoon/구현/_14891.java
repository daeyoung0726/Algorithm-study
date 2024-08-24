package baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _14891 {

    private static String[] wheel;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        wheel = new String[4];

        for (int i = 0; i < 4; i++) {
            wheel[i] = br.readLine();
        }

        StringTokenizer str;
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int wheelNum = Integer.parseInt(str.nextToken()) - 1;
            int dir = Integer.parseInt(str.nextToken());
            int leftDir = dir;
            int rightDir = dir;
            int[] directions = new int[4];
            directions[wheelNum] = dir;

            for (int j = wheelNum - 1; j >= 0; j--) {
                if (!wheelCheck(wheel[j], wheel[j+1])) {
                    leftDir *= -1;
                    directions[j] = leftDir;
                } else
                    break;
            }

            for (int j = wheelNum + 1; j < 4; j++) {
                if (!wheelCheck(wheel[j-1], wheel[j])) {
                    rightDir *= -1;
                    directions[j] = rightDir;
                } else
                    break;
            }

            for (int j = 0; j < 4; j++) {
                if (directions[j] != 0) {
                    wheelSpin(j, directions[j]);
                }
            }

        }

        int sum = 0;
        int x = 1;
        for(String w: wheel) {
            sum += (w.charAt(0) - '0') * x;
            x *= 2;
        }

        System.out.println(sum);
    }

    private static void wheelSpin(int idx, int dir) {

        String w = wheel[idx];

        if (dir == 1) {
            wheel[idx] = w.substring(7) + w.substring(0, 7);
        }
        if (dir == -1) {
            wheel[idx] = w.substring(1) + w.substring(0, 1);
        }
    }

    private static boolean wheelCheck(String wheelA, String wheelB) {
        return wheelA.charAt(2) == wheelB.charAt(6);
    }
}