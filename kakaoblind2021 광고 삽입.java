import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playT = timeToInt(play_time), advT = timeToInt(adv_time);
        long[] timeLine = new long[playT+1];
        
        for(String time : logs) {
            String[] sepT = time.split("-");
            timeLine[timeToInt(sepT[0])]++;
            timeLine[timeToInt(sepT[1])]--;
        }
        
        // 1초 마다 사람의 수
        for(int i = 1 ; i <= playT ; ++i) {
            timeLine[i] += timeLine[i-1];
        }
        // 1초 마다 재생시간누적
        for(int i = 1 ; i <= playT ; ++i) {
            timeLine[i] += timeLine[i-1];
        }
        
        long maxT=timeLine[advT]-timeLine[0];
        int idx=0;
        for(int i=1 ; i+advT <= playT ; ++i) {
            long tmp = timeLine[i+advT]-timeLine[i];
            
            if(tmp > maxT) {
                idx = i+1;
                maxT = tmp;
            }
        }
        
        return String.format("%02d:%02d:%02d", idx/3600, (idx%3600)/60, (idx%3600)%60);
    }
    
    int timeToInt(String time) {
        int[] arr = Arrays.stream(time.split(":")).mapToInt(Integer::parseInt).toArray();
        return arr[0]*3600 + arr[1]*60 + arr[2];
    }
}