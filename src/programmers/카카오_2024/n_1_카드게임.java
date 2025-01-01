package programmers.카카오_2024;

// 턴 수행할 때 마다 2개의 카드를 배열에 담음
// 우산 초기 상태의 카드에서 n + 1만들 수 있으면 그 수를 빼고 넘김
// 못만든다면 초기상태 카드 1장, 새로 들고온 카드에서 1장으로 확인. 가능하다면 코인 1개 사용
// 그것도 안되면 2개 모두 새로운 카드에서 찾기. 있으면 2코인 사용
// 코인도 없다면 끝

import java.util.*;

public class n_1_카드게임 {
    private List<Integer> list;
    private List<Integer> newCard;

    public int solution(int coin, int[] cards) {
        list = new ArrayList<>();
        newCard = new ArrayList<>();

        int idx = cards.length / 3;
        int sumNum = cards[0];

        // n + 1 찾기
        for (int i = 1; i < cards.length; i++) {
            sumNum = Math.max(sumNum, cards[i]);
        }
        sumNum += 1;

        // 초기 카드 넣기
        for (int i = 0; i < idx; i++) {
            list.add(cards[i]);
        }

        // 카드 정렬
        Collections.sort(list);

        int count = 0;
        while (true) {
            boolean isContinue = false;
            count++;

            if (idx + 2 <= cards.length) {
                newCard.add(cards[idx++]);
                newCard.add(cards[idx++]);
                Collections.sort(newCard);
            } else {
                break;
            }

            int[] result1 = findInList(sumNum);
            if (result1[0] != -1)  {
                list.remove((Integer) result1[0]);
                list.remove((Integer) result1[1]);
                isContinue = true;
            } else {
                int[] result2 = findInListAndNewCard(sumNum);
                if (result2[0] != -1 && coin >= 1) {
                    list.remove((Integer) result2[0]);
                    newCard.remove((Integer) result2[1]);
                    coin--;
                    isContinue = true;
                } else if (coin >= 2) {
                    int[] result3 = findInNewCard(sumNum);
                    if (result3[0] != -1) {
                        newCard.remove((Integer) result3[0]);
                        newCard.remove((Integer) result3[1]);
                        coin -= 2;
                        isContinue = true;
                    }
                }
            }

            if (!isContinue)
                break;
        }
        return count;
    }

    private int[] findInList(int n) {
        int start = 0;
        int end = list.size() - 1;

        boolean check = false;
        while (start <= end) {
            int firstNum = list.get(start);
            int secondNum = list.get(end);
            if (firstNum + secondNum == n) {
                check = true;
                break;
            } else if (firstNum + secondNum > n) {
                end--;
            } else {
                start++;
            }
        }

        if (check)
            return new int[] {list.get(start), list.get(end)};
        return new int[] {-1};
    }

    private int[] findInListAndNewCard(int n) {
        int start = 0;

        boolean check = false;
        while (start < newCard.size()) {
            int num = newCard.get(start);

            if (list.contains(n - num)) {
                check = true;
                break;
            }

            start++;
        }

        if (check) {
            return new int[] {n - newCard.get(start), newCard.get(start)};
        }
        return new int[] {-1};
    }

    private int[] findInNewCard(int n) {
        int start = 0;
        int end = newCard.size() - 1;

        boolean check = false;
        while (start <= end) {
            int firstNum = newCard.get(start);
            int secondNum = newCard.get(end);
            if (firstNum + secondNum == n) {
                check = true;
                break;
            } else if (firstNum + secondNum > n) {
                end--;
            } else {
                start++;
            }
        }

        if (check)
            return new int[] {newCard.get(start), newCard.get(end)};
        return new int[] {-1};
    }
}