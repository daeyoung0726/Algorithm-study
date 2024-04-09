package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2941 {
    private final static String[] croatia = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String text = br.readLine();
            for(String str: croatia) {
                text = text.replace(str, "1");
            }

            System.out.println(text.length());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
