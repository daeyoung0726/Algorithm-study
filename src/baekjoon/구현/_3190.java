package baekjoon.구현;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class _3190 {

    private static int n;
    private static int[] dis = { 1, 1, -1, -1 };
    private static int nowVector = 0;
    private static ArrayList<Point> list = new ArrayList<>();
    private static int nowX;
    private static int nowY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        int k = Integer.parseInt(br.readLine());

        StringTokenizer str;
        for (int i = 0; i < k; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(str.nextToken());
            int y = Integer.parseInt(str.nextToken());

            arr[x-1][y-1] = 1;
        }

        int l = Integer.parseInt(br.readLine());
        Turn[] t = new Turn[l];
        for (int i = 0; i < l; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(str.nextToken());
            String c = str.nextToken();
            t[i] = new Turn(a, c);
        }

        int time = 0;
        nowX = 0;
        nowY = 0;
        int turnTime = 0;
        list.add(new Point(nowX, nowY));

        while (true) {
            time++;
            movePoint();

            if (!invalidTest()) {
                break;
            }

            list.add(new Point(nowX, nowY));

            if (turnTime < l && time == t[turnTime].t) {
                if (t[turnTime].vector.equals("L"))
                    nowVector = (nowVector != 0) ? (nowVector - 1) : 3;
                if (t[turnTime].vector.equals("D"))
                    nowVector = (nowVector != 3) ? (nowVector + 1) : 0;
                turnTime++;
            }

            if (arr[nowX][nowY] == 1) {
                arr[nowX][nowY] = 0;
            } else {
                list.remove(0);
            }
        }

        System.out.println(time);
    }

    private static boolean invalidTest() {
        if (nowX < 0 || nowY < 0 || nowX > n-1 || nowY > n-1)
            return false;
        if (list.contains(new Point(nowX, nowY)))
            return false;
        return true;
    }

    private static void movePoint() {
        if (nowVector == 0)
            nowY += dis[0];
        if (nowVector == 1)
            nowX += dis[1];
        if (nowVector == 2)
            nowY += dis[2];
        if (nowVector == 3)
            nowX += dis[3];
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

class Turn {
    int t;
    String vector;

    Turn(int t, String vector) {
        this.t = t;
        this.vector = vector;
    }
}
