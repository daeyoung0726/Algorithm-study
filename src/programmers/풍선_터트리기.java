package programmers;

// 가장자리 2개는 1번만 싸우면 되기에 무조건 가능.
// 중간 값들은 좌우 통틀어 작은값 1개만 있으면 됨.

public class 풍선_터트리기 {

    public int solution(int[] a) {
        int n = a.length;

        int answer = 2;

        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = a[0];

        for (int i = 1; i < n; i++) {
            left[i] = Math.min(left[i - 1], a[i]);
        }

        right[n - 1] = a[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.min(right[i + 1], a[i]);
        }

        for (int i = 1; i < n - 1; i++) {
            if (a[i] < left[i - 1] || a[i] < right[i + 1])
                answer++;
        }
        return answer;
    }
}