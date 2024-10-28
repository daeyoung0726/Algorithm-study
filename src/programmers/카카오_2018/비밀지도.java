package programmers.카카오_2018;

// 겹쳐서 둘 다 공백이면 공백, 둘 중 하나라도 벽이면 벽. -> 그냥 벽만 확인하면 됨
// 암호는 가로를 기준으로 해서, 공백이면 0 벽이면 1. (2진수)
// 10진수를 2진수로 변환하여 각 칸에 0, 1 집어넣기
// 지도 2개 통햇 ||을 통해 값 집어넣기.
// 다 끝나면 0이라면 공백, 1이라면 # 넣기

public class 비밀지도 {
    public String[] solution(int n, int[] arr1, int[] arr2) {

        int[][] map1 = new int[n][n];
        int[][] map2 = new int[n][n];

        int x;
        for (int i = 0; i < n; i++) {
            int num1 = arr1[i];
            int num2 = arr2[i];

            x = n - 1;
            while (num1 > 0) {
                map1[i][x--] = num1 % 2;
                num1 /= 2;
            }

            x = n - 1;
            while (num2 > 0) {
                map2[i][x--] = num2 % 2;
                num2 /= 2;
            }
        }

        int[][] resultMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map1[i][j] == 1 || map2[i][j] == 1) {
                    resultMap[i][j] = 1;
                }
            }
        }

        String[] answer = new String[n];

        StringBuilder sb;

        for (int i = 0; i < n; i++) {
            sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (resultMap[i][j] == 1) {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }
            answer[i] = sb.toString();
        }

        return answer;
    }
}