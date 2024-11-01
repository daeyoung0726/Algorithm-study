package programmers.카카오_2020;

// bfs, d를 통해 세로인지 가로인지 확인 (0이라면 가로, 1이라면 세로 이렇게)
// d에 따라 bfs 다르게 동작
// 가로라면 오른쪽이, 세로라면 아래가 도착했으면, 끝. count를 통해 시간 카운트

import java.util.*;

public class 블록_이동하기 {
    public int solution(int[][] board) {

        int n = board.length;
        int m = board[0].length;

        return bfs(board, n, m);
    }

    private int bfs(int[][] board, int n, int m) {
        Queue<Robot> queue = new LinkedList<>();

        queue.add(new Robot(0, 0, 0, 1, 0, 0)); // 가로는 0, 세로는 1

        boolean[][][][] visited = new boolean[n][m][n][m];

        visited[0][0][0][1] = true;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        while (!queue.isEmpty()) {
            Robot robot = queue.poll();

            if (robot.frontX == n - 1 && robot.frontY == m - 1) {
                return robot.count;
            }

            for (int i = 0; i < 4; i++) {
                int backNX = robot.backX + dx[i];
                int backNY = robot.backY + dy[i];
                int frontNX = robot.frontX + dx[i];
                int frontNY = robot.frontY + dy[i];

                if (backNX >= 0 && backNY >= 0 && backNX < n && backNY < m &&
                        frontNX >= 0 && frontNY >= 0 && frontNX < n && frontNY < m &&
                        !visited[backNX][backNY][frontNX][frontNY]) {
                    if (board[backNX][backNY] == 0 && board[frontNX][frontNY] == 0) {
                        queue.add(new Robot(backNX, backNY, frontNX, frontNY, robot.count + 1, robot.d));
                        visited[backNX][backNY][frontNX][frontNY] = true;
                    }
                }
            }

            if (robot.d == 0) {
                int bx = robot.backX;
                int by = robot.backY;
                int fx = robot.frontX;
                int fy = robot.frontY;

                if (bx - 1 >= 0 && fx - 1 >= 0 && board[bx - 1][by] == 0 && board[fx - 1][fy] == 0) {
                    if (!visited[bx - 1][by][bx][by]) {
                        queue.add(new Robot(bx - 1, by, bx, by, robot.count + 1, 1));
                        visited[bx - 1][by][bx][by] = true;
                    }
                    if (!visited[fx - 1][fy][fx][fy]) {
                        queue.add(new Robot(fx - 1, fy, fx, fy, robot.count + 1, 1));
                        visited[fx - 1][fy][fx][fy] = true;
                    }
                }

                if (bx + 1 < n && fx + 1 < n && board[bx + 1][by] == 0 && board[fx + 1][fy] == 0) {
                    if (!visited[bx][by][bx + 1][by]) {
                        queue.add(new Robot(bx, by, bx + 1, by, robot.count + 1, 1));
                        visited[bx][by][bx + 1][by] = true;
                    }
                    if (!visited[fx][fy][fx + 1][fy]) {
                        queue.add(new Robot(fx, fy, fx + 1, fy, robot.count + 1, 1));
                        visited[fx][fy][fx + 1][fy] = true;
                    }
                }
            } else {    // d == 1
                int bx = robot.backX;
                int by = robot.backY;
                int fx = robot.frontX;
                int fy = robot.frontY;

                if (by - 1 >= 0 && fy - 1 >= 0 && board[bx][by - 1] == 0 && board[fx][fy - 1] == 0) {
                    if (!visited[bx][by - 1][bx][by]) {
                        queue.add(new Robot(bx, by - 1, bx, by, robot.count + 1, 0));
                        visited[bx][by - 1][bx][by] = true;
                    }
                    if (!visited[fx][fy - 1][fx][fy]) {
                        queue.add(new Robot(fx, fy - 1, fx, fy, robot.count + 1, 0));
                        visited[fx][fy - 1][fx][fy] = true;
                    }
                }

                if (by + 1 < n && fy + 1 < n && board[bx][by + 1] == 0 && board[fx][fy + 1] == 0) {
                    if (!visited[bx][by][bx][by + 1]) {
                        queue.add(new Robot(bx, by, bx, by + 1, robot.count + 1, 0));
                        visited[bx][by][bx][by + 1] = true;
                    }
                    if (!visited[fx][fy][fx][fy + 1]) {
                        queue.add(new Robot(fx, fy, fx, fy + 1, robot.count + 1, 0));
                        visited[fx][fy][fx][fy + 1] = true;
                    }
                }
            }
        }

        return -1;
    }
}

class Robot {
    int backX;
    int backY;
    int frontX;
    int frontY;
    int count;
    int d;

    Robot(int backX, int backY, int frontX, int frontY, int count, int d) {
        this.backX = backX;
        this.backY = backY;
        this.frontX = frontX;
        this.frontY = frontY;
        this.count = count;
        this.d = d;
    }
}