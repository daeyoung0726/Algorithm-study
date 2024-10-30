package programmers.카카오_2018;

// LRU 알고리즘. 최근에 사용하지 않은걸 빼겠다.
// 결국엔 큐를 사용해서 contains로 포함되어 있다면 +1 없다면 +5
// 그리고 없다면, cacheSize를 비교한 후, 다 안채워져있으면 그냥 넣고, 아니라면 poll한 후 넣기

import java.util.Queue;
import java.util.LinkedList;

public class 캐시 {
    public int solution(int cacheSize, String[] cities) {

        Queue<String> queue = new LinkedList<>();

        int answer = 0;
        for (int i = 0; i < cities.length; i++) {
            String s = cities[i].toLowerCase();

            if (queue.contains(s)) {
                answer++;
                queue.remove(s);
                queue.add(s);
            } else {
                answer += 5;
                if (cacheSize != 0) {
                    if (queue.size() >= cacheSize) {
                        queue.poll();
                    }
                    queue.add(s);
                }
            }
        }
        return answer;
    }
}