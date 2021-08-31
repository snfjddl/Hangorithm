import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        
        for(int n = 0 ; n<tc ; ++n) {
        	String pat = br.readLine();
        	int strNum = Integer.parseInt(br.readLine());
        	ArrayList<String> list = new ArrayList();
        	for(int i=0 ; i<strNum ; ++i) {
        		String inpStr = br.readLine();
        		if(match(0, 0, pat, inpStr)) {
        			list.add(inpStr);
        		}
        	}
        	
        	list.sort(null);
        	for(String str : list) {
        		sb.append(str).append('\n');
        	}
        }
        
        System.out.print(sb.toString());
    }
    
    public static boolean match(int wpos, int spos, String w, String s) {
    	while(wpos < w.length() && spos < s.length()
    			&& (w.charAt(wpos) == '?' || w.charAt(wpos) == s.charAt(spos))) {
    		wpos++;
    		spos++;
    	}
    	
    	if(wpos == w.length() && spos == s.length()) {
    		return true;
    	}
    	
    	if(wpos < w.length() && w.charAt(wpos) == '*') {
    		if(spos == s.length()) {
    			if(match(wpos+1, spos, w, s)) {
    				return true;
    			}
    		}
			while(spos <= s.length()) {
    			if(match(wpos+1, spos, w, s)) {
    				return true;
    			}
    			spos++;
    		}
    	}
    	
    	return false;
    }
}