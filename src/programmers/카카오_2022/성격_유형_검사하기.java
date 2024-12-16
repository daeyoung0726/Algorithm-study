package programmers.카카오_2022;

// 점수 같으면 사전 순

public class 성격_유형_검사하기 {
    public String solution(String[] survey, int[] choices) {

        int[][] arr = new int[4][2];

        for (int i = 0; i < survey.length; i++) {
            if (survey[i].equals("RT") || survey[i].equals("TR")) {
                int num = Math.abs(4 - choices[i]);
                if (choices[i] > 4) {

                    if (survey[i].equals("RT")) {
                        arr[0][1] += num;
                    } else {
                        arr[0][0] += num;
                    }
                } else {
                    if (survey[i].equals("RT")) {
                        arr[0][0] += num;
                    } else {
                        arr[0][1] += num;
                    }
                }
            }

            if (survey[i].equals("CF") || survey[i].equals("FC")) {
                int num = Math.abs(4 - choices[i]);

                if (choices[i] > 4) {
                    if (survey[i].equals("CF")) {
                        arr[1][1] += num;
                    } else {
                        arr[1][0] += num;
                    }
                } else {
                    if (survey[i].equals("CF")) {
                        arr[1][0] += num;
                    } else {
                        arr[1][1] += num;
                    }
                }
            }

            if (survey[i].equals("JM") || survey[i].equals("MJ")) {
                int num = Math.abs(4 - choices[i]);

                if (choices[i] > 4) {
                    if (survey[i].equals("JM")) {
                        arr[2][1] += num;
                    } else {
                        arr[2][0] += num;
                    }
                } else {
                    if (survey[i].equals("JM")) {
                        arr[2][0] += num;
                    } else {
                        arr[2][1] += num;
                    }
                }
            }

            if (survey[i].equals("AN") || survey[i].equals("NA")) {
                int num = Math.abs(4 - choices[i]);

                if (choices[i] > 4) {
                    if (survey[i].equals("AN")) {
                        arr[3][1] += num;
                    } else {
                        arr[3][0] += num;
                    }
                } else {
                    if (survey[i].equals("AN")) {
                        arr[3][0] += num;
                    } else {
                        arr[3][1] += num;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append((arr[0][0] >= arr[0][1]) ? "R" : "T");
        sb.append((arr[1][0] >= arr[1][1]) ? "C" : "F");
        sb.append((arr[2][0] >= arr[2][1]) ? "J" : "M");
        sb.append((arr[3][0] >= arr[3][1]) ? "A" : "N");

        return sb.toString();
    }
}