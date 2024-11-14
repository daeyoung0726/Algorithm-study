package baekjoon.구현;

// 좌측 상단부터 돌며 부착 가능한 곳을 탐색
// 부착이 불가능하다면 시계방향으로 돌리기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class _18808 {
    private static boolean[][] map;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        map = new boolean[n][m];

        for (int i = 0; i < k; i++) {
            str = new StringTokenizer(br.readLine());
            int stickerX = Integer.parseInt(str.nextToken());
            int stickerY = Integer.parseInt(str.nextToken());

            int[][] sticker = new int[stickerX][stickerY];

            for (int a = 0; a < stickerX; a++) {
                str = new StringTokenizer(br.readLine());
                for (int b = 0; b < stickerY; b++) {
                    sticker[a][b] = Integer.parseInt(str.nextToken());
                }
            }

            attachSticker(sticker, stickerX, stickerY, 0);
        }

        System.out.println(mapCount());
    }

    private static void attachSticker(int[][] sticker, int stickerX, int stickerY, int count) {

        if (count == 4)    // 스티커 시계방향 다 돔.
            return;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (stickerX <= (n - i) && stickerY <= (m - j)) {
                    if (attach(i, j, sticker, stickerX, stickerY)) {
                        return;
                    }
                }
            }
        }


        int[][] newSticker = rotateSticker(sticker, stickerX, stickerY);
        attachSticker(newSticker, newSticker.length, newSticker[0].length, count + 1);

    }

    private static boolean attach(int x, int y, int[][] sticker, int stickerX, int stickerY) {

        for (int i = 0; i < stickerX; i++) {
            for (int j = 0; j < stickerY; j++) {
                if (map[x + i][y + j] && sticker[i][j] == 1)
                    return false;
            }
        }

        for (int i = 0; i < stickerX; i++) {
            for (int j = 0; j < stickerY; j++) {
                if (sticker[i][j] == 1)
                    map[x + i][y + j] = true;
            }
        }

        return true;
    }

    private static int[][] rotateSticker(int[][] sticker, int stickerX, int stickerY) {

        int[][] newSticker = new int[stickerY][stickerX];

        for (int i = 0; i < stickerX; i++) {
            for (int j = 0; j < stickerY; j++) {
                newSticker[j][stickerX - i - 1] = sticker[i][j];
            }
        }

        return newSticker;
    }

    private static int mapCount() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j])
                    count++;
            }
        }

        return count;
    }
}