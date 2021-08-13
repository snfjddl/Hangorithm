class Solution {
    public int lengthOfLongestSubstring(String s) {
        int idx=0, count=0, max=Integer.MIN_VALUE;
        char[] arr = s.toCharArray();
        Map<Character, Integer> charMap = new HashMap();
        
        while(idx < arr.length) {
            char c = arr[idx];
            if(charMap.containsKey(c)) {
                idx = charMap.get(c)+1;
                charMap = new HashMap();
                max = Math.max(max, count);
                count=0;
                continue;
            }
            charMap.put(c, idx);
            count++;
            idx++;
        }
        
        max = Math.max(max, count);
        
        return max;
    }
}