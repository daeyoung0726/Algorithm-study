package 이코테.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 볼링공고르기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        int[] arr = new int[m+1];

        str = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[Integer.parseInt(str.nextToken())]++;
        }

        int result = 0;
        for (int i = 1; i < m+1; i++) {
            n -= arr[i];
            result += arr[i] * n;
        }

        System.out.println(result);
    }
    /*public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        int[] arr = new int[n];

        str = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str.nextToken());
        }

        int result = 0;

        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (arr[i] != arr[j])
                    result++;
            }
        }

        System.out.println(result);
    }*/
}
