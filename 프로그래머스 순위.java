import java.util.*;

class Solution {
    public static int N;
    public static int[][] resultMat;
    public int solution(int n, int[][] results) {
        int answer = 0;
        N = n;
        resultMat = new int[n][n];
        for(int[] result : results) {
            int winner = result[0]-1, loser = result[1]-1;
            resultMat[winner][loser] = 1;
            resultMat[loser][winner] = -1;
        }
        
        // for(int[] arr : resultMat)
        //     System.out.println(Arrays.toString(arr));
        // System.out.println();
        for(int i=0 ; i<n ; ++i) {
            for(int j=0 ; j<n ; ++j) {
                if(i == j)
                    continue;
                
                if(resultMat[i][j] == 1)
                    checkWinResult(i, j);    // 이긴 플레이어의 이긴 플레이어 탐색
                else if(resultMat[i][j] == -1)
                    checkLoseResult(i, j);   // 진 플레이어의 진 플레이어 탐색
            }
        }
        
        // for(int[] arr : resultMat)
        //     System.out.println(Arrays.toString(arr));
        
        for(int[] arr : resultMat) {
            int cnt = 0;
            for(int i=0 ; i<n ; ++i) {
                if(arr[i] != 0)
                    cnt++;
            }
            if(cnt == n-1)
                answer++;
        }
            
        
        return answer;
    }
    
    public static void checkWinResult(int player, int target) {
        for(int i=0 ; i<N ; ++i) {
            if(player == i)
                continue;
            
            if(resultMat[target][i] == 1 && resultMat[player][i] == 0) { // i번째 player를 이겼으면
                resultMat[player][i] = 1;
                checkWinResult(player, i);
            }
        }
    }
    
    public static void checkLoseResult(int player, int target) {
        for(int i=0 ; i<N ; ++i) {
            if(player == i)
                continue;
            
            if(resultMat[target][i] == -1 && resultMat[player][i] == 0) { // i번째 player에게 졌으면
                resultMat[player][i] = -1;
                checkLoseResult(player, i);
            }
        }
    }
}