package programmers.카카오_2024;

// 생성점 : 들어오는거 0개 나가는거 2개 이상
// 도넛 : 전체가 들어오는거 1개 나가는거 1개
// 막대 : 하나가 들어오는거 1개 나가는거 0개
// 8자 : 하나가 들어오는거 2개 나가는거 2개

import java.util.*;

public class 도넛과_막대_그래프 {
    private ArrayList<ArrayList<Integer>> graphs;
    private int[] inDegree;
    private int[] outDegree;

    private int donut = 0;
    private int bar = 0;
    private int eight = 0;

    public int[] solution(int[][] edges) {

        graphs = new ArrayList<>();
        inDegree = new int[1000001];
        outDegree = new int[1000001];

        for (int i = 0; i <= 1000000; i++) {
            graphs.add(new ArrayList<>());
        }

        // in, out 계산
        calcEdges(edges);
        // 생성한 정점 정보 찾기
        int point = findCreatePoint();
        // 그래프 찾기
        run(point);

        return new int[] {point, donut, bar, eight};
    }

    private void calcEdges(int[][] edges) {

        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0];
            int y = edges[i][1];

            graphs.get(x).add(y);
            inDegree[y]++;
            outDegree[x]++;
        }
    }

    private int findCreatePoint() {

        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0 && outDegree[i] >= 2)
                return i;
        }

        return -1;
    }

    private void run(int point) {
        for (int p: graphs.get(point)) {

            boolean check = false;
            inDegree[p]--;

            if ((inDegree[p] == 0 || inDegree[p] == 1) && outDegree[p] == 0) {
                bar++;
                check = true;
            }

            if (!check) {

                int pp = p;
                while (true) {
                    if (graphs.get(pp).isEmpty())
                        break;

                    pp = graphs.get(pp).get(0);
                    if (inDegree[pp] == 1 && outDegree[pp] == 0) {
                        bar++;
                        check = true;
                        break;
                    }
                    if (inDegree[pp] == 2 && outDegree[pp] == 2) {
                        eight++;
                        check = true;
                        break;
                    }

                    if (pp == p)
                        break;
                }
            }

            if (!check) {
                donut++;
            }
        }
    }
}