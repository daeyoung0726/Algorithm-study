package 이코테.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정확한_순위 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        boolean[][] map = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            map[i][i] = true;
        }

        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(str.nextToken());
            int b = Integer.parseInt(str.nextToken());

            map[a-1][b-1] = true;
        }

        floyd(map, n);

        int result = 0;
        int x;
        for (int i = 0; i < n; i++) {
            int count = 0;
            x = 1;
            for (int j = 0; j < n; j++) {
                if (map[i][j] || map[j][i]) {
                    count++;
                    if (i != j && map[i][j])        // 순위 구하기
                        x++;
                }
            }
            if (count == n) {
                result++;
                System.out.println(i+1 + "의 순위: " + x);  // 순위
            }
        }

        System.out.println(result);
    }

    private static void floyd(boolean[][] map, int n) {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][k] && map[k][j]) {
                        map[i][j] = true;
                    }
                }
            }
        }
    }
}
