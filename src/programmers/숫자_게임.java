package programmers;

// a, b 모두 정렬 후 a의 값보다 큰 값

import java.util.*;

public class 숫자_게임 {
    public int solution(int[] A, int[] B) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        Arrays.sort(A);
        Arrays.sort(B);

        int idx = 0;
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            while (idx < B.length) {
                if (B[idx++] > A[i]) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}