package programmers;

// 모든 이름에 대해 Map을 넣고 value를 0으로 초기화. 그리고 seller에서 값을 먼저 넣기.

import java.util.*;

public class 다단계_칫솔_판매 {
    private ArrayList<ArrayList<Integer>> graphs;
    private Map<String, Integer> money;
    private Map<String, Integer> plusMoney;
    private Map<String, Integer> humanIdx;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        humanIdx = createIdx(enroll);

        graphs = new ArrayList<>();
        money = new HashMap<>();
        plusMoney = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            graphs.add(new ArrayList<>());
            money.put(enroll[i], 0);
            plusMoney.put(enroll[i], 0);
        }

        for (int i = 0; i < enroll.length; i++) {
            if (!referral[i].equals("-")) {
                graphs.get(humanIdx.get(enroll[i])).add(humanIdx.get(referral[i]));
            }
        }

        makeMoney(enroll, seller, amount);
        return run(enroll);
    }

    private void makeMoney(String[] enroll, String[] seller, int[] amount) {
        for (int i = 0; i < seller.length; i++) {
            int now = humanIdx.get(seller[i]);
            int num = amount[i] * 100;
            int mod = (int) (num * 0.1);
            money.put(seller[i], money.get(seller[i]) + (num - mod));

            while (!graphs.get(now).isEmpty() && mod != 0) {

                int next = graphs.get(now).get(0);

                int nextMod = (int) (mod * 0.1);
                int plus = mod - nextMod;
                plusMoney.put(enroll[next], plusMoney.get(enroll[next]) + plus);

                mod = nextMod;
                now = next;
            }
        }
    }

    private int[] run(String[] enroll) {
        int[] result = new int[enroll.length];

        for (int i = 0; i < enroll.length; i++) {
            result[i] = money.get(enroll[i]) + plusMoney.get(enroll[i]);
        }

        return result;
    }

    private Map<String, Integer> createIdx(String[] enroll) {
        Map<String, Integer> humanIdx = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            humanIdx.put(enroll[i], i);
        }

        return humanIdx;
    }
}