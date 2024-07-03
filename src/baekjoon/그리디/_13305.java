package baekjoon.그리디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[] dis = new long[n-1];
        long[] price = new long[n];

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int i = 0;
        while(str.hasMoreTokens()) {
            dis[i++] = Long.parseLong(str.nextToken());
        }

        str = new StringTokenizer(br.readLine(), " ");
        i = 0;
        while(str.hasMoreTokens()) {
            price[i++] = Long.parseLong(str.nextToken());
        }

        long minPrice = Integer.MAX_VALUE;
        long answer = 0;
        for(int j = 0; j < n-1; j++) {
            if(minPrice > price[j])
                minPrice = price[j];
            answer += minPrice * dis[j];
        }
        System.out.println(answer);
    }
}