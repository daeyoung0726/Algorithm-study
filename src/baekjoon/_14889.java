package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _14889 {
    private static boolean[] select;
    private static int[] team1;
    private static int[] team2;
    private static int[][] score;
    private static int MIN = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        select = new boolean[n];
        team1 = new int[n/2];
        team2 = new int[n/2];
        score = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++)
                score[i][j] = Integer.parseInt(st.nextToken());
        }

        dfs(n, 0);
        System.out.println(MIN);
    }

    private static void dfs(int n, int idx) {
        if (n/2 == idx) {

            int idx2 = 0;
            for(int i = 0; i < n; i++) {
                if (!select[i])
                    team2[idx2++] = i;
            }

            int team1Score = teamScore(team1);
            int team2Score = teamScore(team2);

            MIN = Math.min(MIN, Math.abs(team1Score-team2Score));
            return;
        }

        for(int i = 0; i < n; i++) {
            if(i != 0 && idx == 0)
                return;
            if(idx != 0 && team1[idx-1] >= i)
                continue;
            if(!select[i]) {
                select[i] = true;
                team1[idx] = i;
                dfs(n, idx+1);
                select[i] = false;
            }
        }
    }

    private static int teamScore(int[] team) {
        int sum = 0;
        for (int i = 0; i < team.length; i++) {
            for (int j = i + 1; j < team.length; j++) {
                sum += score[team[i]][team[j]] + score[team[j]][team[i]];
            }
        }
        return sum;
    }
}
