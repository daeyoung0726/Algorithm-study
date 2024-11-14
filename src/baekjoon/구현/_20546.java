package baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _20546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int bnp, timing;
        bnp = timing = Integer.parseInt(br.readLine());

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int bnpNum = 0;
        int timingNum = 0;

        int preNum = Integer.parseInt(str.nextToken());

        bnpNum = bnp / preNum;
        bnp %= preNum;

        int minusCount = 0;
        int plusCount = 0;

        int num = 0;
        for (int i = 1; i < 14; i++) {
            num = Integer.parseInt(str.nextToken());

            bnpNum += bnp / num;
            bnp %= num;

            if (num > preNum) {
                plusCount++;
                minusCount = 0;
            } else if(num < preNum) {
                minusCount++;
                plusCount = 0;
            } else {
                plusCount = 0;
                minusCount = 0;
            }

            if (minusCount >= 3) {
                timingNum += timing / num;
                timing %= num;
            }
            if (plusCount >= 3) {
                timing += timingNum * num;
                timingNum = 0;
            }
            preNum = num;
        }

        int resultBnp = bnpNum * num + bnp;
        int resultTiming = timingNum * num + timing;
        if (resultBnp > resultTiming) {
            System.out.println("BNP");
        } else if (resultBnp < resultTiming) {
            System.out.println("TIMING");
        } else {
            System.out.println("SAMESAME");
        }
    }
}
