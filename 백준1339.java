import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, Integer> charMap = new HashMap<Character, Integer>();

        int N = Integer.parseInt(br.readLine());
        String[] strings = new String[N];

        for (int i = 0; i < N; i++) {
            strings[i] = br.readLine();
            char[] tmp = strings[i].toCharArray();
            int leng = tmp.length;
            for (int j = 0; j < tmp.length; j++) {
                charMap.put(tmp[j], charMap.getOrDefault(tmp[j], 0) + (int) Math.pow(10, leng-1));
                leng--;
            }
        }
        
        List<Character> keySet = sortByValue(charMap);
        int answer = 0, num=9;
        for(char ch : keySet) {
        	answer += charMap.get(ch) * num--;
        }
        
        System.out.println(answer);
    }
    
    public static List sortByValue(Map map) {
    	List<Map<Character, Integer>> list = new ArrayList();
    	list.addAll(map.keySet());
    	
    	Collections.sort(list, new Comparator<Object>() {
    		@Override
    		public int compare(Object o1, Object o2) {
    			Object v1 = map.get(o1);
    			Object v2 = map.get(o2);
    			
    			return ((Comparable) v2).compareTo(v1);
    		}
    	});
    	
    	return list;
    }
}