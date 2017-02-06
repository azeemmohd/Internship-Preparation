139. Word Break
// https://leetcode.com/problems/word-break/

public boolean wordBreak(String s, Set<String> wordDict) {
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 1; i < dp.length; i++)
        for (int j = 0; j < i; j++) 
            if (dp[j] && wordDict.contains(s.substring(j, i))) {
                dp[i] = true;
                break;
            }
    return dp[s.length()];
}

给一个字符串 such as: "GoogleFacebookMicrosoft", 由字母构成，然后给一个字典，把给定的字符按照字典进行切割，然后输出分割后的一个解答，
例如：dict=['Google', 'Facebook', 'Microsoft', 'GoogleFace', 'bookMicsoft']
如果有多个解答，输出一个即可，对于这个例子显然有两个解答，"Google Facebook Microsoft"， "GoogleFace bookMicrosoft"。随便输出一个就行
我回答，递归可以做，然后给出了答案，分析了复杂度O(n^m)。这里复杂度分析卡了一下，不过还好

followup，有没有其他方法可以做？我说可以DP做，f[i] = True意味着0～i-1存在 matching，为了输出一个solution，用g[i+len(w)] = i记录结果，然后回溯方法可以输出一个答案。interviewer跑了个conner case比较满意。


// http://www.1point3acres.com/bbs/forum.php?mod=redirect&goto=findpost&ptid=207049&pid=2597080&fromuid=96813