import java.util.*;
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> roman = new HashMap();
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);
        
        int answer = 0;
        boolean flag = false;
		for (int i = s.length()-1; i > 0; i--) {
            if(roman.get(s.charAt(i)) > roman.get(s.charAt(i-1))) {
                answer += roman.get(s.charAt(i)) - roman.get(s.charAt(i-1));
                if(i == 1) {flag = true;}
                i--;
            } else {
                answer += roman.get(s.charAt(i));
            }
		}
        
        if(!flag) answer += roman.get(s.charAt(0));
		
		return answer;
	}
}