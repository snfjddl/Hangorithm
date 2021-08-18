import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] mat = new int[rows][columns];
        
        for(int i=0 ; i<rows; ++i) {
            for(int j=0 ; j<columns ; ++j) {
                mat[i][j] = i*columns + j+1;
            }
        }
        
        for(int i=0 ; i<queries.length ; ++i) {
            int x1=queries[i][0]-1, y1=queries[i][1]-1;
            int x2=queries[i][2]-1, y2=queries[i][3]-1;
            int xdiff=Math.abs(x1-x2), ydiff=Math.abs(y1-y2);
            int cnt=0, prev=mat[x1][y1], cur=0;
            int min = prev;
            
            // x1->x2 y1->y2 y2->y1 x2->x1
            while(cnt < ydiff) {
                cur = mat[x1][++y1];
                mat[x1][y1] = prev;
                min = Math.min(min, cur);
                prev = cur;
                cnt++;
            }
            cnt=0;
            while(cnt < xdiff) {
                cur = mat[++x1][y1];
                mat[x1][y1] = prev;
                min = Math.min(min, cur);
                prev = cur;
                cnt++;
            }
            cnt=0;
            while(cnt < ydiff) {
                cur = mat[x2][--y2];
                mat[x2][y2] = prev;
                min = Math.min(min, cur);
                prev = cur;
                cnt++;
            }
            cnt=0;
            while(cnt < xdiff) {
                cur = mat[--x2][y2];
                mat[x2][y2] = prev;
                min = Math.min(min, cur);
                prev = cur;
                cnt++;
            }
            answer[i] = min;
        }
        
        return answer;
    }
}