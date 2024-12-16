package programmers.카카오_2022;

public class k진수에_소수_개수_구하기 {

    public int solution(int n, int k) {

        String trans = Integer.toString(n, k);

        return findNum(trans);
    }

    private int findNum(String trans) {

        int startIdx = 0;
        int count = 0;
        int answer = 0;
        for (int i = 0; i < trans.length(); i++) {
            if (trans.charAt(i) != '0') {
                if (count == 0) {
                    startIdx = i;
                }
                count++;
            } else {
                if (count != 0) {
                    long num = Long.parseLong(
                            trans.substring(startIdx, startIdx + count)
                    );
                    if (isPrime(Long.toString(num, 10))) {
                        answer++;
                    }
                }
                count = 0;
            }
        }

        if (count != 0) {
            long num = Long.parseLong(
                    trans.substring(startIdx, startIdx + count)
            );
            if (isPrime(Long.toString(num, 10))) {
                answer++;
            }
        }

        return answer;
    }

    private boolean isPrime(String n) {
        Long num = Long.parseLong(n);
        if (num <= 1)
            return false;


        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }

        return true;
    }

}