package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class _4779 {
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n;
        int value;
        while((n = br.readLine()) != null) {
            value = 1;
            int a = Integer.parseInt(n);
            for(int i = 0; i < a; i++) {
                value *= 3;
            }
            sb = new StringBuilder();
            for (int i = 0; i < value; i++) {
                sb.append("-");
            }
            cantoa(0, value);
            System.out.println(sb);
        }
    }

    private static void cantoa(int start, int size) {
        if (size == 1)
            return;

        int newSize = size /3;
        for(int i = start + newSize; i < start+newSize*2; i++) {
            sb.setCharAt(i, ' ');
        }

        cantoa(start, newSize);
        cantoa(start+2*newSize, newSize);
    }
}
