package programmers.카카오_2019;

import java.util.*;

public class 호텔_방_배정 {
    private Map<Long, Long> map;

    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];

        map = new HashMap<>();

        for (int i = 0; i < room_number.length; i++) {
            answer[i] = findNum(room_number[i]);
        }
        return answer;
    }

    private long findNum(long roomNum) {
        if (!map.containsKey(roomNum)) {
            map.put(roomNum, roomNum + 1);
            return roomNum;
        }

        long nextRoomNum = findNum(map.get(roomNum));
        map.put(roomNum, nextRoomNum + 1);

        return nextRoomNum;
    }
}