package baekjoon.DFS_BFS;

// 메모리 사용 주의.
// bfs. 고슴도치의 현재 위치 list만을 업데이트. 물의 현재 위치만 업데이트

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _3055 {
    private static char[][] map;
    private static List<int[]> dochi;
    private static List<int[]> water;

    private static boolean isFinish;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        map = new char[n][m];
        dochi = new ArrayList<>();
        water = new ArrayList<>();
        isFinish = false;

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                char c = s.charAt(j);
                if (c == 'S')
                    dochi.add(new int[] {i, j});
                else if (c == '*')
                    water.add(new int[] {i, j});
                map[i][j] = c;
            }
        }

        int time = 0;
        while (true) {
            time++;
            if (dochiBfs(n, m)) {
                if (isFinish)
                    break;
            } else {
                System.out.println("KAKTUS");
                return;
            }
            waterBfs(n, m);
        }

        System.out.println(time);
    }

    private static boolean dochiBfs(int n, int m) {
        List<int[]> temp = new ArrayList<>();

        for (int i = 0; i < dochi.size(); i++) {
            int[] now = dochi.get(i);
            int x = now[0];
            int y = now[1];

            if (map[x][y] == '*')
                continue;

            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;

                if (map[nx][ny] == 'D') {
                    isFinish = true;
                    return true;
                }

                if (map[nx][ny] != '.')
                    continue;

                temp.add(new int[] {nx, ny});
                map[nx][ny] = 'S';
            }
        }

        dochi = temp;

        if (dochi.isEmpty())
            return false;

        return true;
    }

    private static void waterBfs(int n, int m) {
        List<int[]> temp = new ArrayList<>();

        for (int i = 0; i < water.size(); i++) {
            int[] now = water.get(i);
            int x = now[0];
            int y = now[1];

            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;

                if (map[nx][ny] == '*' || map[nx][ny] == 'D' || map[nx][ny] == 'X')
                    continue;

                temp.add(new int[] {nx, ny});
                map[nx][ny] = '*';
            }
        }

        water = temp;
    }
}