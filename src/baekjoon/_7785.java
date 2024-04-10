package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _7785 {
    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            Map<String, Boolean> map = new HashMap<>();

            for(int i = 0; i < n; i++) {
                String[] info = br.readLine().split(" ");
                if(info[1].equals("enter")) {
                    map.put(info[0], true);
                } else if(info[1].equals("leave") && map.containsKey(info[0])) {
                    map.remove(info[0]);
                }
            }
            ArrayList<String> list = new ArrayList<>(map.keySet());
            Collections.sort(list, (s1, s2) -> s2.compareTo(s1));

            for(int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
