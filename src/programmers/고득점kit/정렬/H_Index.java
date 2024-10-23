package programmers.고득점kit.정렬;

import java.util.Arrays;

class H_Index {
    public int solution(int[] citations) {
        int answer = 0;

        Arrays.sort(citations);

        int len = citations.length;
        for (int i = 0; i < len; i++) {
            if (citations[i] >= len - i) {  // len - i은 남은 논문 수. 특정 논문 (citations[i])이 len - i보다 크거나 같아야 나머지 논문들이 모두 len-i보다 크므로, 'citations[i] >= len - i' 해야함. 그리고, 가장 먼저 나타나는 것이 최대값이므로, 바로 break;
                answer = len - i;   // 특정 크기를 h라고 했기에, 그것을 반환
                break;
            }
        }
        return answer;
    }
}