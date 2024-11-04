package baekjoon.DFS_BFS;

// 배열을 만들어 출발위치는 1로 설정
// HashMap을 통해서 key에는 위치 idx, value에는 list형식으로 불을 킬 수 있는 방 idx
// bfs를 진행하고 상하좌우로 움직이며, 불을 킬 수 있는 칸이 나오면, visited를 전체 다 초기화 하여 다시 이동.
// visited 초기화 할 때, queue도 초기화.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _11967 {

    private static int[][] map;
    private static Map<Room, List<Room>> hm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int m = Integer.parseInt(str.nextToken());

        map = new int[n][n];
        map[0][0] = 1;

        hm = new HashMap<>();
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int switchX = Integer.parseInt(str.nextToken()) - 1;
            int switchY = Integer.parseInt(str.nextToken()) - 1;
            int lightX = Integer.parseInt(str.nextToken()) - 1;
            int lightY = Integer.parseInt(str.nextToken()) - 1;

            Room switchRoom = new Room(switchX, switchY);
            if (!hm.containsKey(switchRoom))
                hm.put(switchRoom, new ArrayList<>());

            hm.get(switchRoom).add(new Room(lightX, lightY));
        }

        System.out.println(bfs(n));
    }

    private static int bfs(int n) {
        Queue<Room> queue = new LinkedList<>();

        queue.add(new Room(0, 0));

        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        int count = 1;

        if (hm.containsKey(new Room(0, 0))) {
            for (Room room: hm.get(new Room(0, 0))) {
                if (map[room.x][room.y] == 0) {
                    map[room.x][room.y] = 1;
                    count++;
                }
            }
            hm.remove(new Room(0, 0));
        } else {
            return count;
        }

        while (!queue.isEmpty()) {
            Room now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny] || map[nx][ny] == 0)
                    continue;

                if (hm.containsKey(new Room(nx, ny))) {
                    for (Room room: hm.get(new Room(nx, ny))) {
                        if (map[room.x][room.y] == 0) {
                            map[room.x][room.y] = 1;
                            count++;
                        }
                    }
                    hm.remove(new Room(nx, ny));
                    queue.clear();

                    visited = new boolean[n][n];
                    queue.add(new Room(nx, ny));
                    visited[nx][ny] = true;
                    break;
                } else {
                    queue.add(new Room(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        return count;
    }
}

class Room {
    int x;
    int y;

    Room(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o) {
        Room r = (Room) o;
        return x == r.x && y == r.y;
    }
}