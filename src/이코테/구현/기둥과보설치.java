package 이코테.구현;

import java.util.*;

public class 기둥과보설치 {
    private List<Point> points = new ArrayList<>();

    public int[][] solution(int n, int[][] build_frame) {

        for (int i = 0; i < build_frame.length; i++) {
            int[] frame = build_frame[i];
            if (frame[3] == 1 && addCheck(frame)) {
                points.add(new Point(frame[0], frame[1], frame[2]));
            }
            if (frame[3] == 0 && subCheck(frame))
                points.remove(new Point(frame[0], frame[1], frame[2]));
        }

        Collections.sort(points, (a, b) -> {
            if (a.x != b.x) return a.x - b.x;
            if (a.y != b.y) return a.y - b.y;
            return a.type - b.type;
        });

        int[][] answer = new int[points.size()][3];

        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            answer[i][0] = p.x;
            answer[i][1] = p.y;
            answer[i][2] = p.type;
        }
        return answer;
    }

    private boolean addCheck(int[] frame) {
        int x = frame[0];
        int y = frame[1];
        int type = frame[2];

        if (type == 0) { // 기둥 설치 조건
            if (y == 0 ||
                    points.contains(new Point(x, y - 1, 0)) ||
                    points.contains(new Point(x, y, 1)) ||
                    points.contains(new Point(x - 1, y, 1))) {
                return true;
            }
        } else if (type == 1) { // 보 설치 조건
            if (points.contains(new Point(x, y - 1, 0)) ||
                    points.contains(new Point(x + 1, y - 1, 0)) ||
                    (points.contains(new Point(x - 1, y, 1)) &&
                            points.contains(new Point(x + 1, y, 1)))) {
                return true;
            }
        }
        return false;
    }

    private boolean subCheck(int[] frame) {
        Point toRemove = new Point(frame[0], frame[1], frame[2]);
        points.remove(toRemove);

        for (Point p : points) {
            if (!addCheck(new int[] {p.x, p.y, p.type})) {
                points.add(toRemove); // 다시 원상복구
                return false;
            }
        }
        return true;
    }
}

class Point {
    int x;
    int y;
    int type;

    Point(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        Point p = (Point) o;
        if (this.x == p.x && this.y == p.y && this.type == p.type)
            return true;
        return false;
    }
}