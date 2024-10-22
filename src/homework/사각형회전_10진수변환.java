package homework;

public class 사각형_회전 {
    public static void main(String[] args) {
        정사각형_시계();
        System.out.println();
        정사각형_반시계();
        System.out.println();
        직사각형_시계();
        System.out.println();
        직사각형_반시계();
    }

    private static void 정사각형_시계() {
        int[][] a = new int[3][3];
        int[][] b = new int[3][3];

        int k = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = k++;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b[i][j] = a[3 - j - 1][i];
            }
        }

        for (int[] x: b) {
            for (int val: x) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    private static void 직사각형_시계() {
        int[][] a = new int[2][4];
        int[][] b = new int[4][2];

        int k = 1;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                a[i][j] = k++;
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                b[j][2 - i - 1] = a[i][j];    // 회전 공식 적용     j, n - i - 1 (n은 원배열의 i의 크기)
            }
        }

        for (int[] x: b) {
            for (int val: x) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    private static void 정사각형_반시계() {
        int[][] a = new int[3][3];
        int[][] b = new int[3][3];

        int k = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = k++;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b[i][j] = a[j][3 - i - 1];  // 반시계 방향 회전 공식
            }
        }

        for (int[] x : b) {
            for (int val : x) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    private static void 직사각형_반시계() {
        int[][] a = new int[2][4];
        int[][] b = new int[4][2];

        int k = 1;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                a[i][j] = k++;
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                b[4 - j - 1][i] = a[i][j];  // 반시계 방향 회전 공식
            }
        }

        for (int[] x : b) {
            for (int val : x) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

}
