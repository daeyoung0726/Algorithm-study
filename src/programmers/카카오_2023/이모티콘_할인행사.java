package programmers.카카오_2023;

// 브루트 포스를 통해 이모티콘 개수만큼 배열을 만들어 10, 20, 30, 40퍼센트 할인 적용.
// 만약 4개 다 정했다면, 사용자별로 구매할 거 계산한 후에 업데이트.
// 이모티콘 플러스 서비스 가입이 가장 많은걸 우선. 같아면, 판매액(서비스 가입x)를 최대화

public class 이모티콘_할인행사 {

    private int[] sales;
    private int[][] copyUsers;
    private int[] copyEmotions;

    private int maxService = Integer.MIN_VALUE;
    private int maxPrice = Integer.MIN_VALUE;

    public int[] solution(int[][] users, int[] emoticons) {

        copyUsers = users;
        copyEmotions = emoticons;

        int emoticonsLen = emoticons.length;
        sales = new int[emoticonsLen];

        dfs(0, emoticonsLen);
        return new int[] {maxService, maxPrice};
    }

    private void dfs(int idx, int len) {
        if (idx == len) {
            int countService = 0;
            int countPrice = 0;
            for (int i = 0; i < copyUsers.length; i++) {
                int sum = 0;

                for (int j = 0; j < len; j++) {
                    if (copyUsers[i][0] <= sales[j]) {
                        sum += (copyEmotions[j] - (copyEmotions[j] * sales[j] / 100));
                    }
                }

                if (sum >= copyUsers[i][1]) {
                    countService++;
                } else {
                    countPrice += sum;
                }

                if (countService > maxService ||
                        (countService == maxService && countPrice > maxPrice)) {
                    maxService = countService;
                    maxPrice = countPrice;
                }
            }
            return;
        }

        for (int discount : new int[] {10, 20, 30, 40}) {
            sales[idx] = discount;
            dfs(idx + 1, len);
        }
    }
}