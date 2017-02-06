28. Implement strStr()

public int strStr(String haystack, String needle) {
    // TLE
    // if (needle.length() == 0)     return 0;
    // for (int i = 0; i <= haystack.length(); i++) { 
    //     int j = 0;
    //     for (; j < needle.length(); j++)
    //         if (i + j >= haystack.length() || needle.charAt(j) != haystack.charAt(i + j))     break;
    //     if (j == needle.length())   return i;
    // }
    // return -1;
    // optimized
    if (needle.length() == 0)     return 0; // edge case: "",""=>0  "a",""=>0
    for (int i = 0; i <= haystack.length() - needle.length(); i++) { // possible starting points
        for (int j = 0; j < needle.length() && needle.charAt(j) == haystack.charAt(i + j); j++)
            if (j == needle.length() - 1)   return i;
    }
    return -1;
}