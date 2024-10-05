package programmers.이분탐색;

import java.util.*;

public class 가사_검색 {

    private String[] frontWord;
    private String[] backWord;

    public int[] solution(String[] words, String[] queries) {

        int wordsLen = words.length;
        frontWord = new String[wordsLen];
        backWord = new String[wordsLen];
        int[] countWordLen = new int[10001];

        for (int i = 0; i < wordsLen; i++) {
            frontWord[i] = words[i];
            backWord[i] = new StringBuilder(words[i]).reverse().toString();
            countWordLen[words[i].length()]++;
        }

        Arrays.sort(frontWord);
        Arrays.sort(backWord);

        int count;
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String word;
            int type;

            if (queries[i].charAt(0) == '?') {
                word = new StringBuilder(queries[i]).reverse().toString();
                type = 1;
            }
            else {
                word = queries[i];
                type = 0;
            }

            count = word.indexOf('?');
            String key = word.substring(0, count);


            if (key.isEmpty()) {
                answer[i] = countWordLen[word.length()];
                continue;
            }
            int left = leftBinarySearch(0, wordsLen-1, count, key, type);
            int right = rightBinarySearch(0, wordsLen-1, count, key, type);

            if (right < left) {
                answer[i] = 0;
            } else {
                answer[i] = count(word.length(), left, right, type);
            }
        }

        return answer;
    }

    private int count(int wordLen, int left, int right, int type) {
        String[] temp = null;
        if (type == 0)
            temp = frontWord;
        if (type == 1)
            temp = backWord;

        int count = 0;
        for (int i = left; i <= right; i++) {
            if (temp[i].length() == wordLen)
                count++;
        }

        return count;
    }

    private int leftBinarySearch(int low, int high, int idxLen, String key, int type) {

        String[] temp = null;
        if (type == 0)
            temp = frontWord;
        if (type == 1)
            temp = backWord;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (temp[mid].length() < idxLen) {
                if (temp[mid].compareTo(key) < 0) {
                    low = mid + 1;
                    continue;
                } else {
                    high = mid - 1;
                    continue;
                }
            }

            String t = temp[mid].substring(0, idxLen);
            if (t.compareTo(key) >= 0) {        // 같을 때 high를 내리니깐 반환은 low를 해줘야함. 아니면, high + 1.
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private int rightBinarySearch(int low, int high, int idxLen, String key, int type) {

        String[] temp = null;
        if (type == 0)
            temp = frontWord;
        if (type == 1)
            temp = backWord;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (temp[mid].length() < idxLen) {
                if (temp[mid].compareTo(key) < 0) {
                    low = mid + 1;
                    continue;
                } else {
                    high = mid - 1;
                    continue;
                }
            }

            String t = temp[mid].substring(0, idxLen);
            if (t.compareTo(key) > 0) {
                high = mid - 1;
            } else {
                low = mid + 1;        // 같을 때 low를 올리니깐 반환은 high 해줘야함. 아니면, low - 1.
            }
        }

        return high;
    }
}
