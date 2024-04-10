package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class _10816 {
    public static void main(String[] args) {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            int n = Integer.parseInt(br.readLine());
            Map<Integer, Integer> map = new HashMap<>();
            StringTokenizer str = new StringTokenizer(br.readLine(), " ");

            while(str.hasMoreTokens()) {
                int key = Integer.parseInt(str.nextToken());
                if(map.containsKey(key))
                    map.put(key, map.get(key)+1);
                else
                    map.put(key, 1);
            }

            StringBuilder sb = new StringBuilder();
            int m = Integer.parseInt(br.readLine());
            str = new StringTokenizer(br.readLine(), " ");
            while(str.hasMoreTokens()) {
                int check = Integer.parseInt(str.nextToken());
                if(map.get(check) == null) {
                    sb.append(0).append(" ");
                } else
                    sb.append(map.get(check)).append(" ");
            }

            System.out.print(sb);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
