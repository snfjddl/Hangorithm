import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        Map<String, Integer> map2 = new HashMap<String, Integer>();
        int inter=0, union=0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        for(int i=0 ; i<str1.length()-1 ; ++i) {
            if(str1.charAt(i) >= 'a' && str1.charAt(i) <= 'z'
              && str1.charAt(i+1) >= 'a' && str1.charAt(i+1) <= 'z') {
                String tmp = ""+str1.charAt(i)+str1.charAt(i+1);
                map1.put(tmp, map1.getOrDefault(tmp, 0)+1);
            }
        }
        for(int i=0 ; i<str2.length()-1 ; ++i) {
            if(str2.charAt(i) >= 'a' && str2.charAt(i) <= 'z'
              && str2.charAt(i+1) >= 'a' && str2.charAt(i+1) <= 'z') {
                String tmp = ""+str2.charAt(i)+str2.charAt(i+1);
                map2.put(tmp, map2.getOrDefault(tmp, 0)+1);
            }
        }
        
        for(String key : map1.keySet()) {
            if(map2.containsKey(key)) {
                int val1 = map1.get(key);
                int val2 = map2.get(key);
                
                inter += Math.min(val1, val2);
                union += Math.max(val1, val2);
            } else {
                union += map1.get(key);
            }
        }
        
        for(String key : map2.keySet()) {
            if(!map1.containsKey(key)) {
                union += map2.get(key);
            }
        }
        
        double tmp=0;
        if(union == 0) {
            tmp = 1;
        } else {
            tmp = inter*1.0 / union;
        }
        
        answer = (int) Math.floor(tmp*65536);
        
        return answer;
    }
}