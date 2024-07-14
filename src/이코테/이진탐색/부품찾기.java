package 이코테.이진탐색;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 부품찾기 {

    private static int[] exists;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        exists = new int[N];

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int i = 0;
        while (str.hasMoreTokens()) {
            exists[i++] = Integer.parseInt(str.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] check = new int[M];

        str = new StringTokenizer(br.readLine(), " ");

        i = 0;
        while (str.hasMoreTokens()) {
            check[i++] = Integer.parseInt(str.nextToken());
        }

        Arrays.sort(exists);

        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < M; j++) {
            if (binarySearch(0, N-1, check[j]))
                sb.append("yes ");
            else
                sb.append("no ");
        }

        System.out.println(sb);
    }

    private static boolean binarySearch(int start, int end, int key) {

        while (start <= end) {
            int middle = (start + end) / 2;

            if (exists[middle] == key)
                return true;

            if (exists[middle] < key)
                start = middle + 1;

            if (exists[middle] > key)
                end = middle - 1;
        }

        return false;
    }

    private static boolean binarySearchRecursive(int start, int end, int key) {

        if (start > end)
            return false;

        int middle = (start + end) / 2;

        if (exists[middle] == key)
            return true;

        else if (exists[middle] < key)
            return binarySearchRecursive(middle+1, end, key);

        else
            return binarySearchRecursive(start, middle-1, key);
    }
}
