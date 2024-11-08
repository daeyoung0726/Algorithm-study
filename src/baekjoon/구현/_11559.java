package baekjoon.구현;

// bfs로 근처 확인. (방문한 곳을 true함)
// 만약 4개 이상이면 터트림.
// 위에서부터 열별로 visited가 true인걸 찾고, true라면 버블소트마냥 위치 이동 계속. 옮겨진 위치는 .으로 채우기
// visited는 계속 초기화

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _11559 {

    private static char[][] map;
    private static boolean[][] visited;
    private static ArrayList<int[]> list;

    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[12][6];

        String s;
        for (int i = 0; i < 12; i++) {
            s = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        boolean isContinue;
        int count = 0;
        while (true) {
            visited = new boolean[12][6];
            isContinue = false;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {

                    if (map[i][j] != '.') {
                        list = new ArrayList<>();
                        bfs(i, j, map[i][j]);    // 주변 같은 값 찾기

                        if (list.size() >= 4) {
                            isContinue = true;
                            for (int k = 0; k < list.size(); k++) {
                                int[] now = list.get(k);
                                map[now[0]][now[1]] = '.';
                            }
                        }
                    }
                }
            }

            if (!isContinue) {
                break;
            }
            fallPuyo();
            count++;
        }

        System.out.println(count);
    }

    private static void bfs(int x, int y, char c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});
        list.add(new int[] {x, y});
        visited[x][y] = true;

        int nx, ny;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                nx = now[0] + dx[i];
                ny = now[1] + dy[i];

                if (nx >= 0 && ny >= 0 && nx < 12 && ny < 6 && !visited[nx][ny] && map[nx][ny] == c) {
                    queue.add(new int[] {nx, ny});
                    list.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static void fallPuyo() {

        for (int i = 0; i < 6; i++) {
            for (int j = 11; j > 0; j--) {
                if (map[j][i] == '.') {
                    for (int k = j - 1; k >= 0; k--) {
                        if (map[k][i] != '.') {
                            map[j][i] = map[k][i];
                            map[k][i] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }
}