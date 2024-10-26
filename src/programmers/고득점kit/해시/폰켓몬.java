package programmers.고득점kit.해시;

import java.util.Set;
import java.util.HashSet;

class 폰켓몬 {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int possibleNum = nums.length / 2;

        if (possibleNum <= set.size())
            return possibleNum;
        else
            return set.size();
    }
}