import java.io.*;
import java.util.*;

public class Main { 
	//							←, ↖, ↑, ↗, →, ↘, ↓, ↙
	public static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	public static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	public static int[][] mat;
	public static boolean[][] beforeCloud, afterCloud;
	public static int N, M;
	public static void main(String[] args) throws IOException {
		int answer = 0;
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	mat = new int[N][N];
    	beforeCloud = new boolean[N][N];
    	afterCloud = new boolean[N][N];
    	
    	// 비바라기 시전
    	beforeCloud[N-1][0] = beforeCloud[N-1][1] = beforeCloud[N-2][0] = beforeCloud[N-2][1] = true;
    	
    	for(int i=0 ; i<N ; ++i) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0 ; j<N ; j++) {
    			mat[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	for(int i=0 ; i<M ; ++i) {
    		st = new StringTokenizer(br.readLine());
    		if(i > 0)
    			makeCloud();
    		moveCloud(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    		waterCopy();
    	}
    	makeCloud();
    	
    	for(int[] basket : mat) {
    		for(int water : basket)
    			answer += water;
    	}
    	
    	System.out.print(answer);
    }
	
	public static void waterCopy() {
		int[][] tmp = new int[N][N];
		
		for(int i=0 ; i<N ; ++i) {
    		for(int j=0 ; j<N ; j++) {
    			// dr, dc 2 4 6 8 이용
    			if(afterCloud[i][j]) {
    				for(int k=2 ; k<=8 ; k+=2) {
    					int R = i+dr[k], C = j+dc[k];
    					if(R < 0 || R >= N || C < 0 || C >= N)	continue;
    					else if(mat[R][C] > 0)
    						tmp[i][j]++;
    				}
    			}
    		}
		}
		
		for(int i=0 ; i<N ; ++i) {
    		for(int j=0 ; j<N ; j++) {
    			mat[i][j] += tmp[i][j];
    		}
		}
	}
	
	public static void moveCloud(int direction , int count) {
		int moveR = dr[direction]*count;
		int moveC = dc[direction]*count;

		for(int i=0 ; i<N ; ++i) {
    		for(int j=0 ; j<N ; j++) {
    			if(beforeCloud[i][j]) {
    				int R = i+moveR;
    				int C = j+moveC;
    				
    				while(R < 0)
    					R += N;
    				while(R >= N)
    					R -= N;
    				
    				while(C < 0)
    					C += N;
    				while(C >= N)
    					C -= N;
    				
    				afterCloud[R][C] = true;
    				beforeCloud[i][j] = false;
    				mat[R][C]++;
    			}
    		}
    	}
	}
	
	public static void makeCloud() {
		for(int i=0 ; i<N ; ++i) {
    		for(int j=0 ; j<N ; j++) {
    			if(beforeCloud[i][j])						// 이동 전 구름mat 초기화
    				beforeCloud[i][j] = false;
    			
    			if(!afterCloud[i][j] && mat[i][j] >= 2) {	// 이전에 구름이 없었고 물 양이 2 이상이면 구름 생성
    				beforeCloud[i][j] = true;
    				mat[i][j] -= 2;
    			}
    			else if(afterCloud[i][j]) {					// 이전 구름 제거 
    				afterCloud[i][j] = false;
    			}
    		}
		}
	}
}
