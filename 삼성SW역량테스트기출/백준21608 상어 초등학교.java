import java.io.*;
import java.util.*;

public class Main {
	public static class Coordinate implements Comparable<Coordinate>{
		int x, y, blank;

		public Coordinate(int x, int y, int blank) {
			this.x = x;
			this.y = y;
			this.blank = blank;
		}

		@Override
		public int compareTo(Coordinate o) {
			int rtn = 1;
			int b = o.blank - this.blank;
			if(b == 0) {
				int X = o.x - this.x;
				if(X == 0) {
					int Y = o.y - this.y;
					if(Y > 0)
						rtn = -1;
				}
				else if(X > 0) {
					rtn = -1;
				}
			} 
			else if(b < 0) {
				rtn =  -1;
			}
			
			return rtn;
		}
	}
	
	public static int[] dr = {-1, 0, 1, 0};		// 북동남서
	public static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int studNum = N*N;
    	
    	Set<Integer>[] stud_info = new HashSet[studNum+1];
    	Queue<Integer> seq = new LinkedList<Integer>();
    	
    	for(int i=0 ; i<studNum ; ++i) {
    		String[] tmp = br.readLine().split(" ");
    		int num = Integer.parseInt(tmp[0]);
    		seq.add(num);
    		stud_info[num] = new HashSet<Integer>();
    		stud_info[num].add(Integer.parseInt(tmp[1]));
    		stud_info[num].add(Integer.parseInt(tmp[2]));
    		stud_info[num].add(Integer.parseInt(tmp[3]));
    		stud_info[num].add(Integer.parseInt(tmp[4]));
    	}
    	
    	int[][] mat = new int[N][N];
 	  	while(!seq.isEmpty()) {
 	  		int stud = seq.poll();
 	  		Set likeSet = stud_info[stud];
 	  		ArrayList<Coordinate> likeList = new ArrayList<>();
 	  		int likeMax = 0;

 			// like, blank 조건 탐색 시작
 	  		for(int i=0 ; i<N ; ++i) {
 	    		for(int j=0 ; j<N ; ++j) {
 	    			if(mat[i][j] != 0)
 	    				continue;
 	    			
 	    			int likeTmp = 0, blank = 0;
 	    			for(int k=0 ; k<4 ; ++k) {
 	    				int R = i+dr[k], C = j+dc[k];
 	    				if(R < 0 || R >= N || C < 0 || C >= N)
 	    					continue;
 	    				if(likeSet.contains(mat[R][C])) 
 	    					likeTmp++;
 	    				else if(mat[R][C] == 0)
 	    					blank++;
 	    			}
 	    			
 	    			if(likeMax < likeTmp) {
 	    				likeList.clear();
 	    				likeList.add(new Coordinate(i, j, blank));
 	    				likeMax = likeTmp;
 	    			} else if(likeMax == likeTmp) {
 	    				likeList.add(new Coordinate(i, j, blank));
 	    			}
 	    		}
 	    	}
 	  		// like, blank 조건 탐색 종료
 	  		
 	  		if(likeList.size() == 1) {	// Cond. 1
 	  			Coordinate coor = likeList.get(0);
 	  			mat[coor.x][coor.y] = stud;
 	  		} else {	// Cond. 2
 	  			Collections.sort(likeList);
 	  			Coordinate coor = likeList.get(0);
 	  			mat[coor.x][coor.y] = stud;
 	  		}
 	  	}
 	  	
 	  	System.out.print(calcScore(mat, stud_info));
    }
	
	public static int calcScore(int[][] mat, Set[] set) {
		int leng = mat.length, rtn=0;
		
		for(int i=0 ; i<leng ; ++i) {
			for(int j=0 ; j<leng ; ++j) {
				int count = 0;
				for(int k=0 ; k<4 ; ++k) {
    				int R = i+dr[k], C = j+dc[k];
    				if(R < 0 || R >= leng || C < 0 || C >= leng)
    					continue;
    				else if(set[mat[i][j]].contains(mat[R][C]))
    					count++;
    			}
				
				if(count == 4)
					rtn += 1000;
				else if(count == 3)
					rtn += 100;
				else if(count == 2)
					rtn += 10;
				else if(count == 1)
					rtn += 1;
			}
		}
		
		return rtn;
	}
}

//비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
//1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
//2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.