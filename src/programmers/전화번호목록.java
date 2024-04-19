package programmers;

import java.util.HashMap;
import java.util.Map;

public class 전화번호목록 {
    private static Map<String, Boolean> map;

    public boolean solution(String[] phone_book) {

        boolean answer = true;
        map = new HashMap<>();

        for(String phone : phone_book)
            map.put(phone, true);

        for(int i = 0; i < phone_book.length; i++) {
            answer = checkNumber(phone_book[i]);
            if(answer == false)
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
