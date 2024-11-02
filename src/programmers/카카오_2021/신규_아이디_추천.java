package programmers.카카오_2021;

public class 신규_아이디_추천 {
    public String solution(String new_id) {
        String lowerId = new_id.toLowerCase();

        String pattern = "[^a-z0-9.\\-_]";
        String[] splitsId = lowerId.split(pattern);

        StringBuilder sb = new StringBuilder();

        for (String s: splitsId)
            sb.append(s);

        String temp = sb.toString();

        sb = new StringBuilder();

        String[] tempSplits = temp.split("\\.");

        for (int i = 0; i < tempSplits.length; i++) {
            if (!tempSplits[i].isEmpty()) {
                sb.append(tempSplits[i] + ".");
            }
        }

        String splitWord = sb.toString();

        if (splitWord.isEmpty()) {
            return "aaa";
        }

        boolean start = true;
        boolean end = true;
        int i = 0;
        int j = splitWord.length();
        while (true) {
            if (!start && !end)
                break;

            if (start) {
                if (splitWord.charAt(i) == '.')
                    i++;
                else
                    start = false;
            }

            if (end) {
                if (splitWord.charAt(j - 1) == '.')
                    j--;
                else
                    end = false;
            }
        }

        String newLowerId = splitWord.substring(i, j);

        if (newLowerId.isEmpty()) {
            return "aaa";
        }

        if (newLowerId.length() < 3) {
            if (newLowerId.length() == 1) {
                return newLowerId + newLowerId.charAt(0) + newLowerId.charAt(0);
            } else {
                return newLowerId + newLowerId.charAt(1);
            }
        }

        if (newLowerId.length() > 15) {

            String subword = newLowerId.substring(0, 15);
            if (subword.charAt(14) == '.')
                return subword.substring(0, 14);
            else
                return subword;
        }

        return newLowerId;
    }
}