package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _10988 {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int result = 1;
            String str = br.readLine();
            int len = str.length();
            int low = 0;
            int high = len - 1;
            while(low < high) {
                if(str.charAt(low) != str.charAt(high)) {
                    result = 0;
                    break;
                }
                low++;
                high--;
            }
            System.out.println(result);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
