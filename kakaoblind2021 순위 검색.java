import java.util.*;

class Solution {
    public static Map<String, List<Integer>> infoMap;
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        infoMap = new HashMap<String, List<Integer>>();
        
        for(String userInfo : info) {
            String[] seperateInfo = userInfo.split(" ");
            saveCase(seperateInfo, "", 0);
        }
        
        for(String key : infoMap.keySet()) {
            Collections.sort(infoMap.get(key));
        }
        
        for(int i=0 ; i<answer.length ; ++i) {
            String[] parseStr = query[i].replace(" and ", "").split(" ");
            if(!infoMap.containsKey(parseStr[0])) {
                answer[i] = 0;
                continue;
            }
            List<Integer> list = infoMap.get(parseStr[0]);
            
            int start= 0, end = list.size()-1, score = Integer.parseInt(parseStr[1]);
            while(start<=end) {
                int mid =(start+end)/2;
                
                if(list.get(mid) < score) {
                    start = mid+1;	
                }else {
                    end = mid-1;
                }
            }
            
            answer[i] = list.size() - start;
        }
        
        return answer;
    }
    
    public static void saveCase(String[] Info, String tmpStr, int idx) {
        if(idx == 4) {          
            List<Integer> list = infoMap.getOrDefault(tmpStr, new ArrayList<Integer>());
            
            if(list.size() == 0) {
                list.add(Integer.parseInt(Info[idx]));
                infoMap.put(tmpStr, list);
            } else {
                list.add(Integer.parseInt(Info[idx]));
            }
            
            return ;
        }
        
        saveCase(Info, tmpStr+Info[idx], idx+1);
        saveCase(Info, tmpStr+"-", idx+1);
    }
}