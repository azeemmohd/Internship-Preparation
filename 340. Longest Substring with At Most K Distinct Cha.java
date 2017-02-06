340. Longest Substring with At Most K Distinct Characters
// https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
Given s = “eceba” and k = 2,
return "ece".

public String lengthOfLongestSubstringKDistinct(String s, int k) {
	String res = "";
    int start = 0, end = 0, len = 0, max = 0;
    int[] count = new int[128];
    while (end < s.length()) {
        if (count[s.charAt(end++)]++ == 0)  len++;
        while (len > k) 
            if (count[s.charAt(start++)]-- == 1)    len--;
        if (end - start > max) 
        	res = s.substring(start, end);
    }
    return res;
}