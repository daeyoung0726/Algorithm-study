package 이코테.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 성적순서 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Student> list1 = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer str;
        for (int i = 0; i < n; i++) {
            str = new StringTokenizer(br.readLine(), " ");
            String name = str.nextToken();
            int score = Integer.parseInt(str.nextToken());

            list1.add(new Student(name, score));
        }

        Comparator<Student> cmp = (a, b) -> {
            return a.getScore() - b.getScore();
        };

        Collections.sort(list1, cmp);

        for (Student student: list1) {
            System.out.println(student.getName() + " " + student.getScore());
        }
    }
}

class Student {
    private String name;
    private int score;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    String getName() {
        return this.name;
    }

    int getScore() {
        return this.score;
    }
}
