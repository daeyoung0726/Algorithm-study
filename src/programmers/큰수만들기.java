package programmers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class 큰수만들기 {
    public String solution(String number, int k) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < number.length(); i++) {
            int num = Integer.valueOf(number.charAt(i)) - '0';

            while (list.size() != 0 && k != 0) {
                if (list.get(list.size()-1) < num) {
                    list.remove(list.size()-1);
                    k--;
                } else
                    break;
            }
            list.add(num);
        }

        if (k != 0) {
            while(k > 0) {
                list.remove(list.size()-1);
                k--;
            }
        }

        StringBuilder sb = new StringBuilder();

        Iterator<Integer> itr = list.iterator();
        while (itr.hasNext()) {
            sb.append(itr.next());
        }
        return sb.toString();
    }
}
