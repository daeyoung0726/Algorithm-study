package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _18809 {
    private static int[][] map;
    private static int n;
    private static int m;
    private static List<int[]> possibleList;
    private static int[] land;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());
        int g = Integer.parseInt(str.nextToken());
        int r = Integer.parseInt(str.nextToken());

        map = new int[n][m];
        possibleList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                int num = Integer.parseInt(str.nextToken());
                map[i][j] = num;

                if (num == 2)
                    possibleList.add(new int[] {i, j});
            }
        }

        land = new int[possibleList.size()];

        dfsLand(0, g, r, 0);

        System.out.println(answer);
    }

    private static void dfsLand(int count, int g, int r, int x) {
        if (count == (g + r)) {
            dfsGreen(0, g, 0);
            return;
        }

        for (int i = x; i < possibleList.size(); i++) {
            land[i] = 1;
            dfsLand(count + 1, g, r, i + 1);
            land[i] = 0;
        }
    }

    private static void dfsGreen(int count, int g, int x) {
        if (count == g) {
            answer = Math.max(answer, bfs());
            return;
        }

        for (int i = x; i < possibleList.size(); i++) {
            if (land[i] == 1) {
                land[i] = 2;
                dfsGreen(count + 1, g, i + 1);
                land[i] = 1;
            }
        }
    }

    private static int bfs() {
        Queue<Land> queue = new LinkedList<>();

        int[][] greenLand = new int[n][m];
        int[][] redLand = new int[n][m];

        for (int i = 0; i < land.length; i++) {
            if (land[i] == 2) {
                greenLand[possibleList.get(i)[0]][possibleList.get(i)[1]] = 1;
                queue.add(new Land(possibleList.get(i)[0], possibleList.get(i)[1], 1, 'G'));
            }

            if (land[i] == 1) {
                redLand[possibleList.get(i)[0]][possibleList.get(i)[1]] = 1;
                queue.add(new Land(possibleList.get(i)[0], possibleList.get(i)[1], 1, 'R'));
            }
        }

        int count = 0;

        while (!queue.isEmpty()) {
            Land now = queue.poll();

            if (now.c == 'G') {
                if (greenLand[now.x][now.y] == -1)
                    continue;

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == 0 || greenLand[nx][ny] != 0)
                        continue;

                    if (redLand[nx][ny] == now.time + 1) {
                        count++;
                        greenLand[nx][ny] = -1;
                        redLand[nx][ny] = -1;
                        continue;
                    }

                    if (redLand[nx][ny] != 0)
                        continue;

                    greenLand[nx][ny] = now.time + 1;
                    queue.add(new Land(nx, ny, now.time + 1, 'G'));
                }
            } else if (now.c == 'R') {
                if (redLand[now.x][now.y] == -1)
                    continue;

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == 0 || redLand[nx][ny] != 0)
                        continue;

                    if (greenLand[nx][ny] == now.time + 1) {
                        count++;
                        greenLand[nx][ny] = -1;
                        redLand[nx][ny] = -1;
                        continue;
                    }

                    if (greenLand[nx][ny] != 0)
                        continue;

                    redLand[nx][ny] = now.time + 1;
                    queue.add(new Land(nx, ny, now.time + 1, 'R'));
                }
            }
        }

        return count;
    }
}

class Land {
    int x;
    int y;
    int time;
    char c;

    Land(int x, int y, int time, char c) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.c = c;
    }
}