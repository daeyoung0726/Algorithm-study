package baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int h = Integer.parseInt(str.nextToken());
        int w = Integer.parseInt(str.nextToken());

        int[] wall = new int[w];

        str = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < w; i++) {
            int high = Integer.parseInt(str.nextToken());
            if (high > h)
                throw new RuntimeException("잘못된 입력입니다.");
            wall[i] = high;
        }

        int sum = 0;
        for (int i = 1; i < w-1; i++) {
            int leftWall = 0;
            int rightWall = 0;

            for (int j = 0; j < i; j++) {
                leftWall = Math.max(leftWall, wall[j]);
            }

            for (int j = i+1; j < w; j++) {
                rightWall = Math.max(rightWall, wall[j]);
            }

            int minWall = Math.min(leftWall, rightWall);

            if (minWall > wall[i])
                sum += minWall - wall[i];
        }

        System.out.println(sum);
    }
}
