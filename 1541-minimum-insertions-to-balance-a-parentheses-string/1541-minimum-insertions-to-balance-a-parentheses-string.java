class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openNeeded = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                openNeeded++;
            } else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    insertions++;
                }

                if (openNeeded > 0) {
                    openNeeded--;
                } else {
                    insertions++;
                }
            }
        }

        return insertions + openNeeded * 2;
    }
}