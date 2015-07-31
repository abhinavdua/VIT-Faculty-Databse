package com.abhinav.facultydatabasedev;

public class StringMatcher {

	public static boolean match(String value, String keyword) {
        if (value == null || keyword == null)
            return false;
        if (keyword.length() > value.length())
            return false;

        int i = 0, j = 0;
        do {
            int vi = value.charAt(i);
            int kj = keyword.charAt(j);
            if (isKorean(vi) && isInitialSound(kj)) {
            } else {
                if (vi == kj) {
                    i++;
                    j++;
                } else if (j > 0)
                    break;
                else
                    i++;
            }
        } while (i < value.length() && j < keyword.length());

        return (j == keyword.length())? true : false;
    }

    private static boolean isKorean(int i) {
        return false;
    }

    private static boolean isInitialSound(int i) {
        return false;
    }
	
}
