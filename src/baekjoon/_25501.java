package baekjoon;

import java.util.Scanner;

public class _25501 {
    static int count = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            String s = sc.next();
            sb.append(isPalindrome(s)).append(" ").append(count).append('\n');
        }
        System.out.println(sb);
    }

    private static int isPalindrome(String s) {
        count = 0;
        return recursion(s, 0, s.length()-1);
    }

    private static int recursion(String s, int start, int end) {
        count++;
        if(s.charAt(start) != s.charAt(end))
            return 0;
        if(start >= end)
            return 1;

        return recursion(s, start+1, end-1);
    }
}
