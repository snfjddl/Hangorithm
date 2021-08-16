class Solution {
    public String lastTime(int hour,int min,int crueNum,int maximum,int[][] timesCnt) {
        String rtn="";
        String hStr="", mStr="";
        boolean flag = false;
        int m = min;
        
        for(int h=hour ; h>=0 ; --h) {
            if(flag)    break;
            while(m >= 0) {
                crueNum -= timesCnt[h][m];
                m--;
                
                if(crueNum <= maximum) {
                    rtn = makeTime(h, m);
                    flag = true;
                    break;
                }
            }
            m = 59;
        }
        
        return rtn;
    }
    
    public String makeTime(int hour, int min) {
        String hStr="", mStr="";
        
        if(min < 0) {
            mStr = "59";
            hour--;
        } else if(min >= 0 && min < 10) {
            mStr = "0"+min;
        } else {
            mStr = min+"";
        }

        if(hour >= 0 && hour < 10) {
            hStr = "0"+hour;
        } else {
            hStr = hour+"";
        }
        
        return hStr+":"+mStr;
    }
    
    
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int[][] timesCnt = new int[24][60];
        for(String str : timetable) {
            int hour = Integer.parseInt(str.substring(0,2));
            int min = Integer.parseInt(str.substring(3));
            timesCnt[hour][min]++;
        }
        
        int crueNum = 0;
        int maximum = n*m;
        boolean flag = false;
        // 9시 까지 탑승자리가 남아있을지 탐색
        for(int hour=0 ; hour<9 ; ++hour) {
            if(flag)
                break;
            for(int min=0 ; min<60 ; ++min) {
                crueNum += timesCnt[hour][min];
                if(crueNum >= maximum) {
                    answer = lastTime(hour, min, crueNum, maximum, timesCnt);
                    flag = true;
                    break;
                }
            }
        }
        
        // 9시까지 탑승 자리가 남아있다면 배차 시작
        int count=0;
        if(answer.equals("")) {
            flag = false;
            
            for(int hour=9 ; hour<24 ; ++hour) {
                if(flag)   break;
                for(int min=0 ; min<60 ; ++min) {
                    crueNum += timesCnt[hour][min];
                    if(count % t == 0) {    // 버스 도착
                        if(n > 1) {         
                            crueNum = crueNum >= m ? crueNum-m : 0;    
                        } else {            // 막차 
                            crueNum -= m;   
                        }
                        maximum -= m;    
                        n--;
                    }
                    
                    if(n > 0 && crueNum >= maximum) {
                        answer = lastTime(hour, min, crueNum, maximum, timesCnt);
                        flag = true;
                        break;
                    } else if(n == 0) {
                        if(crueNum >= maximum) {
                            answer = makeTime(hour, min-1);
                        } else {
                            answer = makeTime(hour, min);
                        }
                        flag = true;
                        break;
                    }
                    count++;
                }
            }
        }
        
        return answer;
    }
}