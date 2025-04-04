package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _3197 {
    private static char[][] map;
    private static boolean[][] visited;
    private static Queue<int[]> waterQueue = new LinkedList<>();
    private static Queue<int[]> swanQueue = new LinkedList<>();
    private static boolean[][] swanVisited;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    private static int r, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine(), " ");

        r = Integer.parseInt(str.nextToken());
        c = Integer.parseInt(str.nextToken());

        map = new char[r][c];
        swanVisited = new boolean[r][c];
        visited = new boolean[r][c];
        int[] swan1 = null, swan2 = null;

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] != 'X') {
                    waterQueue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
                if (map[i][j] == 'L') {
                    if (swan1 == null) {
                        swan1 = new int[]{i, j};
                    } else {
                        swan2 = new int[]{i, j};
                    }
                }
            }
        }

        swanQueue.add(swan1);
        swanVisited[swan1[0]][swan1[1]] = true;

        int days = 0;
        while (true) {
            if (canMeet(swan2)) {
                System.out.println(days);
                break;
            }
            meltIce();
            days++;
        }
    }

    private static boolean canMeet(int[] swan2) {
        Queue<int[]> nextQueue = new LinkedList<>();
        while (!swanQueue.isEmpty()) {
            int[] now = swanQueue.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c || swanVisited[nx][ny]) {
                    continue;
                }

                swanVisited[nx][ny] = true;
                if (map[nx][ny] == 'X') {
                    nextQueue.add(new int[]{nx, ny});
                } else if (nx == swan2[0] && ny == swan2[1]) {
                    return true;
                } else {
                    swanQueue.add(new int[]{nx, ny});
                }
            }
        }
        swanQueue = nextQueue;
        return false;
    }

    private static void meltIce() {
        int size = waterQueue.size();
        for (int i = 0; i < size; i++) {
            int[] now = waterQueue.poll();
            int x = now[0];
            int y = now[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || nx >= r || ny < 0 || ny >= c || visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] == 'X') {
                    map[nx][ny] = '.';
                    waterQueue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}
