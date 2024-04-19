package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class _11478 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Boolean> map = new HashMap<>();

        String str = br.readLine();
        int n = str.length();
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n+1; j++) {
                String check = str.substring(i, j);
                if(!map.containsKey(check))
                    map.put(check, true);
            }
        }
        System.out.println(map.size());
    }
}
