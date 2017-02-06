49. Group Anagrams

public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
        char[] chrs = s.toCharArray();
        Arrays.sort(chrs);
        String t = new String(chrs);
        if (!map.containsKey(t))    map.put(t, new ArrayList<>());
        map.get(t).add(s);
    }
    return new ArrayList<List<String>>(map.values());
}