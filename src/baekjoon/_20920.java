package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _20920 {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer str = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(str.nextToken());
            int m = Integer.parseInt(str.nextToken());

            Map<String, Integer> map = new HashMap<>();
            for(int i = 0; i < n; i++) {
                String word = br.readLine();
                if(word.length() >= m) {
                    if(map.containsKey(word)) {
                        map.put(word, map.get(word)+1);
                    } else {
                        map.put(word, 1);
                    }
                }
            }
            String[][] arr = new String[map.size()][2];

            int j = 0;

            StringBuilder sb = new StringBuilder();
            for(String key : map.keySet()) {
                arr[j][0] = key;
                arr[j++][1] = String.valueOf(map.get(key));
            }

            Comparator<String[]> cmp = (a, b) -> {
                if(!a[1].equals(b[1])) {
                    return Integer.parseInt(b[1]) - Integer.parseInt(a[1]);
                } else {
                    if(a[0].length() != b[0].length())
                        return b[0].length() - a[0].length();
                    else
                        return a[0].compareTo(b[0]);
                }
            };

            Arrays.sort(arr, cmp);

            for(int i = 0; i < arr.length; i++) {
                sb.append(arr[i][0]).append('\n');
            }
            System.out.print(sb);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
