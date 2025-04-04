package baekjoon.DFS_BFS;

// bfs. 사람 먼저 이동 시키기 -> 불 확산(불은 list에 담아두기)
// 꺼냈을때, 가장자리이고 해당 구역이 불이 없으면 return

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _5427 {
    private static char[][] map;
    private static List<int[]> human;
    private static List<int[]> fire;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringTokenizer str;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            str = new StringTokenizer(br.readLine(), " ");

            int m = Integer.parseInt(str.nextToken());
            int n = Integer.parseInt(str.nextToken());

            map = new char[n][m];
            human = new ArrayList<>();
            fire = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                String s = br.readLine();
                for (int k = 0; k < m; k++) {
                    char c = s.charAt(k);
                    if (c == '@') {
                        human.add(new int[] {j, k});
                    }
                    if (c == '*') {
                        fire.add(new int[] {j, k});
                    }
                    map[j][k] = c;
                }
            }

            int count = 0;
            while (true) {
                count++;
                if (humanBfs(n, m)) {
                    break;
                } else {
                    if (human.isEmpty()) {
                        count = -1;
                        break;
                    }
                }

                fireBfs(n, m);
            }

            if (count == -1)
                sb.append("IMPOSSIBLE \n");
            else
                sb.append(count + "\n");
        }

        System.out.println(sb);
    }

    private static boolean humanBfs(int n, int m) {
        List<int[]> temp = new ArrayList<>();

        for (int i = 0; i < human.size(); i++) {
            int[] now = human.get(i);
            int x = now[0];
            int y = now[1];

            if (map[x][y] == '*')
                continue;

            if (check(x, y, n, m))
                return true;

            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] != '.')
                    continue;

                map[nx][ny] = '@';
                temp.add(new int[] {nx, ny});
            }

        }
        human = temp;
        return false;
    }

    private static void fireBfs(int n, int m) {
        List<int[]> temp = new ArrayList<>();

        for (int i = 0; i < fire.size(); i++) {
            int[] now = fire.get(i);
            int x = now[0];
            int y = now[1];

            for (int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == '#' || map[nx][ny] == '*')
                    continue;

                map[nx][ny] = '*';
                temp.add(new int[] {nx, ny});
            }
        }

        fire = temp;
    }

    private static boolean check(int x, int y, int n, int m) {
        return x == n - 1 || x == 0 || y == m - 1 || y == 0;
    }
}