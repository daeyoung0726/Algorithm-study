package programmers.카카오_2023;

// 먼 곳 부터 줘야함
// 배달, 수거할 곳 스택에 넣고, 위에서 부터 꺼낸 후 cap에 맞게 사용.
// 꺼낸 위치에서 가장 먼 곳까지 이동. (왔다 갔다이니 x 2)

import java.util.Stack;

public class 택배_배달과_수거하기 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {

        Stack<int[]> deliver = new Stack<>();
        Stack<int[]> pickup = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (deliveries[i] != 0)
                deliver.push(new int[] {i + 1, deliveries[i]});
            if (pickups[i] != 0)
                pickup.push(new int[] {i + 1, pickups[i]});
        }

        long answer = 0;
        while (!deliver.isEmpty() || !pickup.isEmpty()) {
            int maxIdx = -1;

            if (!deliver.isEmpty()) {
                int copyCap = cap;
                while (true) {
                    if (deliver.isEmpty() || copyCap == 0)
                        break;

                    int[] now = deliver.pop();
                    maxIdx = Math.max(maxIdx, now[0]);

                    if (now[1] > copyCap) {
                        deliver.push(new int[] {now[0], now[1] - copyCap});
                        break;
                    }
                    copyCap -= now[1];
                }
            }

            if (!pickup.isEmpty()) {
                int copyCap = cap;
                while (true) {
                    if (pickup.isEmpty() || copyCap == 0)
                        break;

                    int[] now = pickup.pop();
                    maxIdx = Math.max(maxIdx, now[0]);

                    if (now[1] > copyCap) {
                        pickup.push(new int[] {now[0], now[1] - copyCap});
                        break;
                    }
                    copyCap -= now[1];
                }
            }

            answer += maxIdx * 2;
        }
        return answer;
    }
}