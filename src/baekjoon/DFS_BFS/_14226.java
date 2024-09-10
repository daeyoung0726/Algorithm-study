package baekjoon.DFS_BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _14226 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        System.out.println(bfs(1, n));
    }

    private static int bfs(int start, int n) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[2001][2001]; // 화면, 클립보드 상태 기록
        queue.add(new int[] { start, 0, 0 }); // {화면의 이모티콘 수, 클립보드의 이모티콘 수, 시간}

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int screen = now[0];
            int clipBoard = now[1];
            int count = now[2];

            if (screen == n) {
                return count;
            }

            // 1. 복사 (현재 화면 -> 클립보드)
            if (!visited[screen][screen]) {
                visited[screen][screen] = true;
                queue.add(new int[] { screen, screen, count + 1 });
            }

            // 2. 붙여넣기 (클립보드 -> 화면에 붙여넣기)
            if (clipBoard > 0 && screen + clipBoard <= 2000 && !visited[screen + clipBoard][clipBoard]) {
                visited[screen + clipBoard][clipBoard] = true;
                queue.add(new int[] { screen + clipBoard, clipBoard, count + 1 });
            }

            // 3. 화면에서 이모티콘 하나 삭제
            if (screen > 1 && !visited[screen - 1][clipBoard]) {
                visited[screen - 1][clipBoard] = true;
                queue.add(new int[] { screen - 1, clipBoard, count + 1 });
            }
        }

        return -1;
    }
}