package programmers.고득점kit.해시;

import java.util.HashMap;
import java.util.Map;

public class 전화번호_목록 {
    private static Map<String, Boolean> map;

    public boolean solution(String[] phone_book) {

        boolean answer = true;
        map = new HashMap<>();

        for(String phone : phone_book)
            map.put(phone, true);

        for(int i = 0; i < phone_book.length; i++) {
            if(!checkNumber(phone_book[i]))
                break;
        }

        return answer;
    }

    private boolean checkNumber(String phoneNum) {

        int n = phoneNum.length();
        for(int i = 1; i < n; i++) {
            String substring = phoneNum.substring(0, i);
            if(map.containsKey(substring))
                return false;
        }
        return true;
    }
}

/*
import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {

        Arrays.sort(phone_book, (a, b) -> a.compareTo(b));
        boolean answer = true;


        for (int i = 1; i < phone_book.length; i++) {
            if (phone_book[i].startsWith(phone_book[i-1])) {
                answer = false;
                break;
            }
        }

        return answer;
    }
}
 */
