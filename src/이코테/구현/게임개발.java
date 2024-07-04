package 이코테.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 게임개발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(str.nextToken());
        int M = Integer.parseInt(str.nextToken());

        str = new StringTokenizer(br.readLine(), " ");
        int row = Integer.parseInt(str.nextToken());
        int col = Integer.parseInt(str.nextToken());
        int direction = Integer.parseInt(str.nextToken());

        int[][] location = new int[N][M];
        for (int i = 0; i < N; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int j = 0;
            while (str.hasMoreTokens()) {
                location[i][j++] = Integer.parseInt(str.nextToken());
            }
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        int answer = 1;
        int count = 0;
        location[row][col] = -1;

        while (true) {

            direction = (direction > 0) ? direction - 1 : 3;
            count++;

            int nx = row + dx[direction];
            int ny = col + dy[direction];

            if (nx > 0 && ny > 0 && nx < N && ny < M && !check(location[nx][ny])) {
                answer++;
                count = 0;
                row = nx;
                col = ny;
                location[nx][ny] = -1;
            }

            if (count == 4) {
                int bx = row - dx[direction];
                int by = col - dy[direction];

                if (checkSea(location[bx][by]))
                    break;
                else {
                    row = bx;
                    col = by;
                    count = 0;
                }
            }

        }

        System.out.println(answer);
    }

    private static boolean check(int location) {
        return location == 1 || location == -1;
    }

    private static boolean checkSea(int location) {
        return location == 1;
    }
}
