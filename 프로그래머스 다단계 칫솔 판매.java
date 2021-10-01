import java.util.*;

class Solution {
    public static Map<String, Integer> resultMap;
    public static Map<String, String> referralMap;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        resultMap = new HashMap<String, Integer>();
        referralMap = new HashMap<String, String>();
        
        for(int i=0 ; i<enroll.length ; ++i) {
            resultMap.put(enroll[i], 0);
            String referralName = "";
            if(referral[i].equals("-"))
                referralName = "center";
            else
                referralName = referral[i];
            
            referralMap.put(enroll[i], referralName);
        }
        resultMap.put("center", 0);
        
        for(int i=0 ; i<seller.length ; ++i) {
            dfs(seller[i], amount[i]*100);
        }
        
        for(int i=0 ; i<enroll.length ; ++i) {
            answer[i] = resultMap.get(enroll[i]);
        }
        
        return answer;
    }
    
    public static void dfs(String seller, int amount) {
        if(seller.equals("center"))
            return;
        
        String parent = referralMap.getOrDefault(seller, "");

        if(!parent.equals("")) {    // 추천인이 존재하면
            int toRef = (int)(amount*0.1);
            int my = amount - toRef;
            if(toRef == 0) {
                resultMap.put(seller, resultMap.get(seller)+amount);
            } else {
                resultMap.put(seller, resultMap.get(seller)+my);
                dfs(parent, toRef);
            }
        } else {    // 추천인이 존재하지 않으면 판매자가 전부 가짐
            resultMap.put(seller, resultMap.get(seller)+amount);
        }
    }
}

// result Map<Name, amount>
// referral Map<추천인, 참여자>
// referral map에서 seller를 value로 가지는 referral 탐색해서 +10% 반복