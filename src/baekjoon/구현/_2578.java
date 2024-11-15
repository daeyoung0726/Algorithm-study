package baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _2578 {

    private static int[][] map;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        visited = new boolean[5][5];
        map = new int[5][5];
        StringTokenizer str;

        for (int i = 0; i < 5; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(str.nextToken());
            }
        }

        int num = 0;
        int k = 0;
        boolean check = false;
        for (int i = 0; i < 5; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++) {
                k++;
                num = Integer.parseInt(str.nextToken());
                if (findNumAndBingo(num) == 3) {
                    check = true;
                    break;
                }
            }
            if (check)
                break;
        }
        System.out.println(k);
    }

    private static int findNumAndBingo(int num) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == num) {
                    visited[i][j] = true;
                    break;
                }
            }
        }
        return checkBingo();
    }

    private static int checkBingo() {

        boolean check;
        int count = 0;

        for (int i = 0; i < 5; i++) {
            check = true;
            for (int j = 0; j < 5; j++) {
                if (!visited[i][j]) {
                    check = false;
                    break;
                }
            }
            if (check)
                count++;
        }

        for (int j = 0; j < 5; j++) {
            check = true;
            for (int i = 0; i < 5; i++) {
                if (!visited[i][j]) {
                    check = false;
                    break;
                }
            }
            if (check)
                count++;
        }

        if (visited[0][0] && visited[1][1] && visited[2][2] && visited[3][3] && visited[4][4])
            count++;

        if (visited[4][0] && visited[3][1] && visited[2][2] && visited[1][3] && visited[0][4])
            count++;

        return count;
    }
}