package programmers.카카오_2024;

// a, b 사이에 선물 전달이 적은 사람이 많은 사람에게 1개 줌
// 같거나 없다면 선물 지수(선물한 전체 개수 - 받은 전체 개수)가 적은 사람이 큰 사람에게
// 이거까지 같다면 안줌.

import java.util.*;

public class 가장_많이_받은_선물 {
    private int[] giftCount;
    private int[][] giftMap;

    public int solution(String[] friends, String[] gifts) {

        int len = friends.length;
        giftCount = new int[len];
        giftMap = new int[len][len];

        Map<String, Integer> friendsMap = new HashMap<>();

        for (int i = 0; i < len; i++) {
            friendsMap.put(friends[i], i);
        }

        // 선물 주고받기
        calcGift(friendsMap, gifts);
        // 선물 지수 구하기
        Map<String, Integer> giftIdx = createGiftIdx(friendsMap, friends);
        // 다음 달 선물
        run(friendsMap, giftIdx, friends);

        int answer = -1;

        for (int val: giftCount) {
            answer = Math.max(answer, val);
        }

        return answer;
    }

    private void calcGift(Map<String, Integer> friendsMap, String[] gifts) {

        for (int i = 0; i < gifts.length; i++) {
            String[] split = gifts[i].split(" ");

            int fromIdx = friendsMap.get(split[0]);
            int toIdx = friendsMap.get(split[1]);

            giftMap[fromIdx][toIdx]++;
        }
    }

    private Map<String, Integer> createGiftIdx(Map<String, Integer> friendsMap, String[] friends) {

        Map<String, Integer> giftIdx = new HashMap<>();

        for (int i = 0; i < friends.length; i++) {
            int idx = friendsMap.get(friends[i]);

            int sum = 0;

            for (int j = 0; j < giftMap[idx].length; j++) {
                sum += giftMap[idx][j];
                sum -= giftMap[j][idx];
            }

            giftIdx.put(friends[i], sum);
        }

        return giftIdx;
    }

    private void run(Map<String, Integer> friendsMap, Map<String, Integer> giftIdx, String[] friends) {
        for (int i = 0; i < friends.length; i++) {
            int idx = friendsMap.get(friends[i]);

            int count = 0;
            for (int j = 0; j < friends.length; j++) {
                if (i == j)
                    continue;

                if (giftMap[idx][j] > giftMap[j][idx] || (giftMap[idx][j] == giftMap[j][idx] && giftIdx.get(friends[i]) > giftIdx.get(friends[j])))
                    count++;
            }

            giftCount[i] = count;
        }
    }
}