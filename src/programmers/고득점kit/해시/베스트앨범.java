package programmers.고득점kit.해시;

import java.util.*;

class 베스트앨범 {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genre = new HashMap<>();
        Map<String, Integer> genreIdx = new HashMap<>();
        ArrayList<ArrayList<int[]>> list = new ArrayList<>();

        int count = 0;

        for (int i = 0; i < genres.length; i++) {
            if (genre.containsKey(genres[i])) {
                genre.put(genres[i], genre.get(genres[i]) + plays[i]);
                list.get(genreIdx.get(genres[i])).add(new int[] {i, plays[i]});
            } else {
                genre.put(genres[i], plays[i]);
                genreIdx.put(genres[i], count);
                list.add(new ArrayList<>());
                list.get(count).add(new int[] {i, plays[i]});
                count++;
            }
        }

        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < genres.length; i++) {
            if (genre.get(genres[i]) != 0) {
                result.add(new int[] {genreIdx.get(genres[i]), genre.get(genres[i])});
                genre.put(genres[i], 0);
            }
        }

        Collections.sort(result, (a, b) -> b[1] - a[1]);
        for (int i = 0; i < list.size(); i++) {
            Collections.sort(list.get(i), (a, b) -> b[1] - a[1]);
        }

        List<Integer> l = new ArrayList<>();

        int x = 0;
        for (int i = 0; i < result.size(); i++) {

            l.add(list.get(result.get(i)[0]).get(0)[0]);
            if (list.get(result.get(i)[0]).size() > 1)
                l.add(list.get(result.get(i)[0]).get(1)[0]);
        }

        int[] answer = new int[l.size()];

        for (int i = 0; i < l.size(); i++) {
            answer[i] = l.get(i);
        }

        return answer;
    }
}