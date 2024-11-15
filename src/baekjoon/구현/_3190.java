package baekjoon.구현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _3190 {

    private static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        map = new int[n][n];
        StringTokenizer str;

        for (int i = 0; i < k; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(str.nextToken()) - 1;
            int y = Integer.parseInt(str.nextToken()) - 1;
            map[x][y] = 1;
        }

        int l = Integer.parseInt(br.readLine());

        Node12[] nodes = new Node12[l];

        for (int i = 0; i < l; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(str.nextToken());
            char dir = str.nextToken().charAt(0);

            nodes[i] = new Node12(time, dir);
        }

        System.out.println(dummyRun(nodes, n));
    }

    private static int dummyRun(Node12[] nodes, int n) {
        Deque<Point> deque = new LinkedList<>();

        deque.add(new Point(0, 0));

        int[] dx = { 0, 1, 0, -1 };
        int[] dy = { 1, 0, -1, 0 };

        int dir = 0;
        int time = 0;
        int idx = 0;

        while (true) {
            Point now = deque.peekLast();
            int nx = now.x + dx[dir];
            int ny = now.y + dy[dir];

            time++;

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                break;
            }

            if (deque.contains(new Point(nx, ny))) {
                break;
            }

            if (map[nx][ny] == 1) {
                map[nx][ny] = 0;
            } else {
                deque.poll();
            }
            deque.add(new Point(nx, ny));

            if (idx < nodes.length && time == nodes[idx].time) {
                if (nodes[idx].dir == 'D') {
                    dir = (dir + 1) % 4;
                }
                if (nodes[idx].dir == 'L') {
                    dir = (dir - 1) >= 0 ? (dir - 1) : 3;
                }
                idx++;
            }
        }

        return time;
    }
}

class Node12 {
    int time;
    char dir;

    Node12(int time, char dir) {
        this.time = time;
        this.dir = dir;
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        Point p = (Point) o;
        if (this.x == p.x && this.y == p.y)
            return true;
        return false;
    }
}
