package baekjoon.구현;

// 동, 서, 남, 북에 대한 이동 4개 만들기
// 동쪽으로 이동하다가 벽 만다며, 서쪽으로 보내기. 모든 방향 다 그렇게.
// 상어 이동 속도 넣을 떄, 동 서 는 % 2 * x - 2, 남북은 % 2 * y - 2 하기. 원래 자리로 돌아오는 거임
// list에 상어를 넣을 떄, 상어 크기 순으로 정렬.
// 1. 상어가 현재 위치에 있는지 확인하는건 visited. 그리고, 이동할 떄 마다 visited를 초기화.
//    삭제할 값 따로 저장 후 마지막에 삭제 (메모리 망함)
// 2. 다 이동시킨 후, 큰 값부터, 상어들 다 잡아먹기 (시간 망함)    // 7시 20분

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _17143 {

    private static ArrayList<Shark> sharks;
    private static int n, m;

    // 방향 배열: 1->위, 2->아래, 3->오른쪽, 4->왼쪽
    private static int[] dx = {0, -1, 1, 0, 0};
    private static int[] dy = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(str.nextToken());
        m = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        sharks = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(str.nextToken()) - 1;
            int c = Integer.parseInt(str.nextToken()) - 1;
            int s = Integer.parseInt(str.nextToken());
            int d = Integer.parseInt(str.nextToken());
            int z = Integer.parseInt(str.nextToken());
            sharks.add(new Shark(r, c, s, d, z));
        }

        int sum = 0;
        for (int i = 0; i < m; i++) { // 낚시왕이 오른쪽으로 한 칸씩 이동
            // 1. 상어 잡기
            sum += catchShark(i);

            // 2. 상어 이동
            moveAllSharks();

            // 3. 상어 겹침 처리
            resolveConflicts();
        }

        System.out.println(sum);
    }

    // 상어를 잡는 함수: 해당 열에 있는 상어 중 가장 가까운 상어를 잡음
    private static int catchShark(int col) {
        int minRow = Integer.MAX_VALUE;
        int idx = -1;
        for (int i = 0; i < sharks.size(); i++) {
            Shark shark = sharks.get(i);
            if (shark.c == col && shark.r < minRow) {
                minRow = shark.r;
                idx = i;
            }
        }

        if (idx != -1) {
            int size = sharks.get(idx).z;
            sharks.remove(idx);
            return size; // 잡은 상어 크기를 리턴
        }
        return 0;
    }

    // 모든 상어 이동 처리
    private static void moveAllSharks() {
        for (Shark shark : sharks) {
            moveShark(shark);
        }
    }

    // 상어 이동 로직
    private static void moveShark(Shark shark) {
        int r = shark.r;
        int c = shark.c;
        int s = shark.s;
        int d = shark.d;

        // 상하 이동 시에는 (n - 1) * 2, 좌우 이동 시에는 (m - 1) * 2 주기 반복
        int cycle = (d == 1 || d == 2) ? (n - 1) * 2 : (m - 1) * 2;
        s = s % cycle;

        for (int i = 0; i < s; i++) {
            if (d == 1 && r == 0) d = 2;
            else if (d == 2 && r == n - 1) d = 1;
            else if (d == 3 && c == m - 1) d = 4;
            else if (d == 4 && c == 0) d = 3;

            if (d == 1) {
                r -= 1;
            }
            if (d == 2) {
                r += 1;
            }

            if (d == 3) {
                c += 1;
            }
            if (d == 4) {
                c -= 1;
            }
        }

        shark.r = r;
        shark.c = c;
        shark.d = d;
    }

    // 상어 겹침 처리: 같은 위치에 상어가 여러 마리 있을 때 크기 큰 상어만 남기기
    private static void resolveConflicts() {
        Shark[][] grid = new Shark[n][m];

        // 상어를 격자판에 배치
        for (Shark shark : sharks) {
            int r = shark.r;
            int c = shark.c;

            if (grid[r][c] == null || grid[r][c].z < shark.z) {
                grid[r][c] = shark; // 더 큰 상어만 남김
            }
        }

        // 리스트 갱신: 크기 큰 상어들만 남기기
        sharks.clear();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != null) {
                    sharks.add(grid[i][j]);
                }
            }
        }
    }
}

// 상어 클래스 정의
class Shark {
    int r, c, s, d, z;

    Shark(int r, int c, int s, int d, int z) {
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
    }
}
