package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HW2 {

    private static boolean[] visit;
    private static int[] arr;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("정수 n과 k를 입력? ");
        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        visit = new boolean[n];
        arr = new int[k];

        recursion(n, k, 0);

        System.out.println(sb);
    }

    private static void recursion(int n, int k, int depth) {
        if(k == depth) {
            sb.append("[");
            for(int i = 0; i < arr.length; i++) {
                sb.append(arr[i]);
                if(i != arr.length-1)
                    sb.append(", ");
                else
                    sb.append("] ");
            }
            return;
        }

        for(int i = 0; i < n; i++) {
            if(!visit[i]) {
                if(depth >= 1 && arr[depth-1] > i+1)
                    continue;
                visit[i] = true;
                arr[depth] = i + 1;
                recursion(n, k, depth+1);
                visit[i] = false;
            }
        }

    }
}
