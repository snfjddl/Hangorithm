import java.io.*;
import java.util.*;

public class Main { 
	public static int answer;
	public static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	public static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	public static class Coor {
		int row, col, dir;
		Coor(int row, int col, int dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = 0;
		Map<Integer, Coor> fish = new TreeMap();
		int[][] mat = new int[4][4];	// 0이면 빈칸, -1이면 상어
		for(int i=0 ; i<4 ; ++i) {
			String[] tmp = br.readLine().split(" ");
			for(int j=0 ; j<4 ; ++j) {
				int num = Integer.parseInt(tmp[j*2]);
				int dir = Integer.parseInt(tmp[j*2+1])-1;
				mat[i][j] = num;
				fish.put(num, new Coor(i, j, dir));
			}
		}
		
		// 청소년 상어 등장
		int first_fish = mat[0][0];
		int shark_dir = fish.get(first_fish).dir;
		mat[0][0] = -1;
		fish.remove(first_fish);
		
		search(0, 0, shark_dir, first_fish, mat, fish);
		
		
		System.out.println(answer);
    }
	
	public static void search(int row, int col, int dir, int score, int[][] fish_mat, Map<Integer, Coor> map) {	// params == 상어의 위치와 방향
		int R=row, C=col;
		
		int[][] mat = new int[4][4];
		for(int i=0 ; i<4 ; ++i) {
			for(int j=0 ; j<4 ; ++j) {
				mat[i][j] = fish_mat[i][j];
			}
		}

		Map<Integer, Coor> fish = new TreeMap();
		for(int num : map.keySet()) {
			Coor tmp = map.get(num);
			fish.put(num, new Coor(tmp.row, tmp.col, tmp.dir));
		}
		
		fish_move(mat, fish);
		
		while(R >= 0 && R < 4 && C >= 0 && C < 4) {
			R += dr[dir];
			C += dc[dir];
			if((R >= 0 && R < 4 && C >= 0 && C < 4) && mat[R][C] > 0) {	// 물고기가 있으면
				int fish_num = mat[R][C];
				Coor fish_coor = fish.get(fish_num);
				mat[row][col] = 0;
				mat[R][C] = -1;
				fish.remove(fish_num);
				search(R, C, fish_coor.dir, score+fish_num, mat, fish);
				mat[R][C] = fish_num;
				mat[row][col] = -1;
				fish.put(fish_num, fish_coor);
			}
		}

		answer = Math.max(answer, score);
	}
	
	public static void fish_move(int[][] mat, Map<Integer, Coor> fish) {
		for(int fish_num : fish.keySet()) {
			Coor fish_coor = fish.get(fish_num);
			int R = fish_coor.row;
			int C = fish_coor.col;
			int dir = fish_coor.dir;
			
			while(true) {		// 이동할 수 있을때까지 방향전환
				int row = R+dr[dir];
				int col = C+dc[dir];
				if((row >= 0 && row < 4 && col >= 0 && col < 4) && mat[row][col] >= 0) {	// 범위 안 && 상어가 없는 곳
					fish_coor.row = row;
					fish_coor.col = col;
					fish_coor.dir = dir;
					if(mat[row][col] > 0) {	// row, col에 물고기가 있으면
						int change_fish = mat[row][col];
						Coor change_coor = fish.get(change_fish);
						mat[R][C] = change_fish;
						mat[row][col] = fish_num;
						change_coor.row = R;
						change_coor.col = C;
						fish.put(fish_num, fish_coor);
						fish.put(change_fish, change_coor);
					} else {
						mat[R][C] = 0;
						mat[row][col] = fish_num;
						fish.put(fish_num, fish_coor);
					}
					break;
				}
				dir = (dir+1)%8;
			}
		}
	}
}

// 번호가 낮은 물고기부터 이동. => Map<물고기 번호, 위치>
// 상어, 벽이면 반시계로 45도 방향전환 == dir+1
// 물고기가 있으면 위치교환, 빈 공간이면 그냥 이동
