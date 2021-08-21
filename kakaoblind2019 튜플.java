import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String s) {
        int[] answer;
        StringBuilder sb = new StringBuilder();
        Map<Integer, Integer> map = new HashMap();
        int tmp=0;
        char prev = '{';
        
        for(int i=1 ; i<s.length()-1 ; ++i) {
            switch(s.charAt(i)) {
                case '{':
                    prev = '{';
                    break;
                case ',':
                    if(prev != '}') {
                        tmp = Integer.parseInt(sb.toString());
                        map.put(tmp, map.getOrDefault(tmp, 0) + 1);
                        sb = new StringBuilder();    
                    }
                    prev = ',';
                    break;
                case '}':
                    prev = '}';
                    tmp = Integer.parseInt(sb.toString());
                    map.put(tmp, map.getOrDefault(tmp, 0) + 1);
                    sb = new StringBuilder();
                    break;
                default:
                    sb.append(s.charAt(i));
            }
        }
        
        List<Integer> list = sortByValue(map);
        answer = new int[list.size()];
        
        for(int i=0 ; i<list.size() ; ++i) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    public List<Integer> sortByValue(Map map) {
        ArrayList<Integer> list = new ArrayList();
        list.addAll(map.keySet());
        
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                Integer v1 = (Integer) map.get(o1);
                Integer v2 = (Integer) map.get(o2);
                
                return v2.compareTo(v1);
            }
        });
        
        return list;
    }
}