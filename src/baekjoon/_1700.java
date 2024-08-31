package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class _1700 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer str = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(str.nextToken());
        int k = Integer.parseInt(str.nextToken());

        int[] sequence = new int[k];
        List<Integer> now = new ArrayList<>();

        str = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            sequence[i] = Integer.parseInt(str.nextToken());
        }

        int count = 0;

        for (int i = 0; i < k; i++) {
            int device = sequence[i];

            // 이미 꽂혀있는 경우
            if (now.contains(device)) {
                continue;
            }

            // 멀티탭에 자리가 남아있으면 꽂음
            if (now.size() < n) {
                now.add(device);
                continue;
            }

            // 멀티탭이 꽉 찬 경우, 가장 나중에 사용되거나 사용되지 않는 기기 제거
            int deviceToUnplug = -1;
            int lastUsageIndex = -1;

            for (int pluggedDevice : now) {
                int nextUsageIndex = Integer.MAX_VALUE;

                for (int j = i + 1; j < k; j++) {
                    if (sequence[j] == pluggedDevice) {
                        nextUsageIndex = j;
                        break;
                    }
                }

                if (nextUsageIndex > lastUsageIndex) {
                    lastUsageIndex = nextUsageIndex;
                    deviceToUnplug = pluggedDevice;
                }
            }

            now.remove((Integer) deviceToUnplug);
            now.add(device);
            count++;
        }

        System.out.println(count);
    }
}
