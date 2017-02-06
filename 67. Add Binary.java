67. Add Binary
// https://leetcode.com/problems/add-binary/

a = "11"
b = "1"
Return "100".

public String addBinary(String a, String b) {
    StringBuilder sb = new StringBuilder();
    int i = a.length() - 1, j = b.length() - 1;
    int sum = 0;
    while (i >= 0 || j >= 0) {
        sum /= 2;
        if (i >= 0)     sum += a.charAt(i--) - '0';
        if (j >= 0)     sum += b.charAt(j--) - '0';
        sb.append(sum % 2);
    }
    if (sum / 2 != 0)   sb.append(sum / 2);
    return sb.reverse().toString();
}
