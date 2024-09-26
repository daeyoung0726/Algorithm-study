package baekjoon.정렬;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _10825 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer str;

        PriorityQueue<Student> pq = new PriorityQueue<>((a, b) -> {
            if (a.kor == b.kor) {
                if (a.eng == b.eng) {
                    if (a.math == b.math) {
                        return a.name.compareTo(b.name);    // 이름사전 순으로 증가하는 순서대로.
                    }
                    return b.math - a.math; // 수학 점수가 감소하는 순서대로.
                }
                return a.eng - b.eng;   // 영어 점수가 증가하는 순서대로.
            }
            return b.kor - a.kor;   // 국어 점수 감소하는 순서대로.
        });

        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            String name = str.nextToken();
            int kor = Integer.parseInt(str.nextToken());
            int eng = Integer.parseInt(str.nextToken());
            int math = Integer.parseInt(str.nextToken());

            pq.add(new Student(name, kor, eng, math));
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll().name).append('\n');
        }

        System.out.println(sb);
    }
}

class Student {
    String name;
    int kor;
    int eng;
    int math;

    Student(String name, int kor, int eng, int math) {
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }
}