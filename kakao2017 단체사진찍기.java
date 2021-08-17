import java.util.*;

class Solution {
    static Map<Character, Integer> kakaoPos = new HashMap();
    static int[] cases = new int[8];
    static boolean[] used = new boolean[8];
    static String[] datas;
    static int count;
    
    public int solution(int n, String[] data) {
        int answer = 0;
        datas = data;
        count = 0;
        
        kakaoPos.put('A', 0);
        kakaoPos.put('C', 1);
        kakaoPos.put('F', 2);
        kakaoPos.put('J', 3);
        kakaoPos.put('M', 4);
        kakaoPos.put('N', 5);
        kakaoPos.put('R', 6);
        kakaoPos.put('T', 7);
        
        search(0);
        answer = count;
        
        return answer;
    }
    
    public void search(int idx) {
        if(idx == 8) {
            validPos();
            return ;
        }
        
        for(int i=0 ; i<8 ; ++i) {
            if(!used[i]) {
                used[i] = true;
                cases[idx] = i;
                search(idx+1);
                used[i] = false;
            }
        }
    }
    
    public void validPos() {
        for(String str : datas) {
            int sPos = cases[kakaoPos.get(str.charAt(0))];
            int tPos = cases[kakaoPos.get(str.charAt(2))];
            char oper = str.charAt(3);
            int dist = str.charAt(4)-48+1;
            
            int diff = Math.abs(sPos-tPos);
            switch(oper) {
                case '=':
                    if(diff != dist) { return; }
                    break;
                case '<':
                    if(diff >= dist) { return; }
                    break;
                case '>':
                    if(diff <= dist) { return; }
                    break;
            }
        }
        count++;
    }
}