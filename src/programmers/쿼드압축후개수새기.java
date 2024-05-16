package programmers;

public class 쿼드압축후개수새기 {
    class Solution {

        private int zeroNum = 0;
        private int oneNum = 0;

        public int[] solution(int[][] arr) {

            int firstNum = arr[0][0];
            boolean firstCheck = true;
            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < arr.length; j++) {
                    if(arr[i][j] != firstNum) {
                        firstCheck = false;
                        break;
                    }
                }
            }

            if(firstCheck) {
                if(firstNum == 0)
                    zeroNum++;
                else
                    oneNum++;
            } else {
                int size = arr.length;
                int mid = size / 2;

                recursion(arr, 0, mid-1, 0, mid-1, size/2);
                recursion(arr, mid, size-1, 0, mid-1, size/2);
                recursion(arr, 0, mid-1, mid, size-1, size/2);
                recursion(arr, mid, size-1, mid, size-1, size/2);
            }

            int[] answer = {zeroNum, oneNum};
            return answer;
        }

        private void recursion(int[][] arr, int xStart, int xEnd, int yStart, int yEnd, int size) {
            if(size == 1) {
                if(arr[xStart][yStart] == 0)
                    zeroNum++;
                else
                    oneNum++;
                return;
            }

            int firstNum = arr[xStart][yStart];

            for(int i = xStart; i <= xEnd; i++) {
                for(int j = yStart; j <= yEnd; j++) {
                    if(arr[i][j] != firstNum) {
                        int xMid = (xStart + xEnd) / 2;
                        int yMid = (yStart + yEnd) / 2;

                        recursion(arr, xStart, xMid, yStart, yMid, size/2);
                        recursion(arr, xMid+1, xEnd, yStart, yMid, size/2);
                        recursion(arr, xStart, xMid, yMid+1, yEnd, size/2);
                        recursion(arr, xMid+1, xEnd, yMid+1, yEnd, size/2);

                        return;
                    }
                }
            }

            if(firstNum == 0)
                zeroNum++;
            else
                oneNum++;
        }
    }
}
