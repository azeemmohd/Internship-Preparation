394. Decode String
// https://leetcode.com/problems/decode-string/

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

// digit: push into count stack, 
// letter: update res, 
// [: push res to res stack, 
// ]: pop out of res stack and append res count times

public String decodeString(String s) {
    String res = "";
    Stack<String> resStack = new Stack<>();
    Stack<Integer> countStack = new Stack<>();
    int i = 0;
    while (i < s.length()) {
        char c = s.charAt(i);
        if (Character.isDigit(c)) {
            int count = 0;
            while (Character.isDigit(s.charAt(i))) 
                count = count * 10 + s.charAt(i++) - '0';
            countStack.push(count);
        } else if (s.charAt(i) == '[') {
            resStack.push(res);
            res = "";
            i++;
        } else if (s.charAt(i) == ']') {
            StringBuilder tmp = new StringBuilder(resStack.pop());
            int count = countStack.pop();
            while (count-- > 0) 
                tmp.append(res);
            res = tmp.toString();
            i++;
        } else {
            StringBuilder sb = new StringBuilder(res);
            sb.append(s.charAt(i++));
            res = sb.toString();
        }
    }
    return res;
}