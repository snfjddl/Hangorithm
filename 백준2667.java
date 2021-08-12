import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[][] mat;
	static boolean[][] visit;
	static int N, count;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> answer = new ArrayList();
        N = Integer.parseInt(br.readLine());
        mat = new int[N][N];
        visit = new boolean[N][N];
        
        for (int i = 0; i < N; ++i) {
        	char[] tmpArr = br.readLine().toCharArray();
			for(int j=0 ; j<N ; ++j) {
				mat[i][j] = tmpArr[j]-48;
			}
		}
        
        for(int i=0 ; i<N ; ++i) {
        	for(int j=0 ; j<N ; ++j) {
            	count = 0;
        		if(mat[i][j] == 1 && !visit[i][j]) {
        			dfs(i,j);
        			answer.add(count);
        		}
        	}
        }
        
        answer.sort(null);
        System.out.println(answer.size());
        for(int i : answer)
        	System.out.println(i);
    }
    
    public static void dfs(int row, int col) {
    	if(visit[row][col] || mat[row][col] == 0) {
    		return ;
    	}
    	
    	if(mat[row][col] == 1) {
    		visit[row][col] = true;
    		count++;
    	}
    	
    	for(int i=0 ; i<4 ; ++i) {
    		int R = row+dr[i];
    		int C = col+dc[i];
    		
    		if(R >= 0 && R < N && C >= 0 && C < N) {
    			dfs(R, C);
    		}
    	}
    }
}

