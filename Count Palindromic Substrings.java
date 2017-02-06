Count Palindromic Substrings
找一个字符串所有回文子字符串的个数

public static int longestPalindrome(String s) {
    int start = 0, end = 0, res = 0;
    for (int i = 0; i < s.length() - 1; i++) {
        int count1 = countExpandPalindromes(s, i, i);
        int count2 = countExpandPalindromes(s, i, i + 1);
        res += count1 + count2;
    }
    return res;
}
private static int countExpandPalindromes(String s, int i, int j) {
    while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
        i--;
        j++;
    }
    return (j - i) / 2;
}


伪代码
for i from 0 through n - 1
     for r that makes both i - r and i + r in range 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
          if charAt(i - r) != charAt(i + r) break;
          count++;

for i from 0 though n - 2
     for r that makes both i - r and i + 1 + r in range
          if charAt(i - r) == charAt(i + 1 + r) break
          count++;

return count;