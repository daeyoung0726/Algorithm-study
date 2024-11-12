package baekjoon.구현;

// 동쪽으로 이동할 때 정보를 세대별로 구해놓기.
// 세대별로 나올 떄, 값 불러와서 for문으로 값 넣기
// 방향별로 해당 값들에 대해 값을 더하기 (0 동, 1 북, 2 서, 3 남)
// 꼭지점 찾는 것은 그냥 for문으로 돌리기 (0 <= x <= 98 && 0 <= y <= 98)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _15685 {

    private static boolean[][] visited = new boolean[101][101]; // 드래곤 커브가 방문한 좌표
    private static int[] dx = {1, 0, -1, 0}; // 0: 오른쪽, 1: 위쪽, 2: 왼쪽, 3: 아래쪽
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()); // 시작 방향
            int g = Integer.parseInt(st.nextToken()); // 세대

            makeDragonCurve(x, y, d, g);
        }

        System.out.println(countSquares()); // 결과 출력
    }

    private static void makeDragonCurve(int x, int y, int d, int g) {
        List<Integer> dirs = new ArrayList<>();
        dirs.add(d);

        int size;
        for (int i = 0; i < g; i++) {
            size = dirs.size();

            for (int j = size - 1; j >= 0; j--) {
                dirs.add((dirs.get(j) + 1) % 4);
            }
        }

        visited[x][y] = true;

        for (int i = 0; i < dirs.size(); i++) {
            x += dx[dirs.get(i)];
            y += dy[dirs.get(i)];

            if (x < 0 || y < 0 || x > 100 || y > 100)
                break;
            visited[x][y] = true;
        }
    }

    private static int countSquares() {
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // 1x1 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인지 확인
                if (visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]) {
                    count++;
                }
            }
        }
        return count;
    }
}