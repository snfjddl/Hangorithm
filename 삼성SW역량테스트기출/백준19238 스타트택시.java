import java.io.*;
import java.util.*;

public class Main {
	public static int[][] mat;
	public static Coordinate[][] passMat;
	public static int N, M, passengers, fuel;
	public static int[] dr = {-1, 0, 0, 1};		// 북 서 동 남
	public static int[] dc = {0, -1, 1, 0};

	static class Coordinate {
		public int Row, Col, Dist;

		public Coordinate(int Row, int Col, int Dist) {
			this.Row = Row;
			this.Col = Col;
			this.Dist = Dist;
		}
	}

	public static void main(String[] args) throws IOException {
    	int answer = -1;
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	String[] NMF = br.readLine().split(" ");
    	N = Integer.parseInt(NMF[0]);
    	M = Integer.parseInt(NMF[1]);
    	passengers = M;
    	fuel = Integer.parseInt(NMF[2]);
    	
    	passMat = new Coordinate[N][N];
    	mat = new int[N][N];
    	for(int i=0 ; i<N ; ++i) {
    		String[] tmp = br.readLine().split(" ");
    		for(int j=0 ; j<N ; ++j)
    			mat[i][j] = Integer.parseInt(tmp[j]);
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	int taxiR = Integer.parseInt(st.nextToken())-1;
    	int taxiC = Integer.parseInt(st.nextToken())-1;
    	
    	for(int i=0 ; i<M ; ++i) {	// mat의 idx가 0부터 시작하므로 주어진 좌표들 -1하여 저장
    		String[] tmp = br.readLine().split(" ");
    		passMat[Integer.parseInt(tmp[0])-1][Integer.parseInt(tmp[1])-1] = new Coordinate(Integer.parseInt(tmp[2])-1, Integer.parseInt(tmp[3])-1, 0);
    	}
    	
    	bfsPassenger(taxiR, taxiC);
    	System.out.print(fuel);
    }

	public static void bfsPassenger(int row, int col) {
    	Queue<Coordinate> que = new LinkedList<Coordinate>();
    	boolean[][] visit = new boolean[N][N];
    	que.add(new Coordinate(row, col, 0));
    	
    	while(!que.isEmpty()) {
    		Coordinate Coor = que.poll();
    		if(Coor.Row < 0 || Coor.Row >= N || Coor.Col < 0 || Coor.Col >= N || mat[Coor.Row][Coor.Col] == 1)	// 범위를 벗어나거나 벽이면 cont
    			continue;
    		else if(visit[Coor.Row][Coor.Col])		// 이미 방문했으면 cont
    			continue;
    		
    		visit[Coor.Row][Coor.Col] = true;
    		if(passMat[Coor.Row][Coor.Col] != null)	{	// 승객 찾았으면 Goal로 데려다주기
    			// 같은 거리의 위치 모두 탐색하여 다른 승객이 있는지 확인, 우선순위에 따라 승객 선택
    			while(!que.isEmpty()) {
    				Coordinate tmp = que.poll();
    				if(tmp.Row < 0 || tmp.Row >= N || tmp.Col < 0 || tmp.Col >= N || mat[tmp.Row][tmp.Col] == 1)	// 범위를 벗어나거나 벽이면 cont
    	    			continue;
    				
    				if(passMat[tmp.Row][tmp.Col] != null && Coor.Dist == tmp.Dist) {
    					if(Coor.Row > tmp.Row) {
    						Coor = tmp;
    						continue;
    					}
    					else if(Coor.Row == tmp.Row && Coor.Col > tmp.Col) {
    						Coor = tmp;
    						continue;
    					}
    				}
    			}
    			// 같은 거리의 위치 모두 탐색하여 다른 승객이 있는지 확인, 우선순위에 따라 승객 선택
    			fuel -= Coor.Dist;
    			if(fuel <= 0) {	// 승객에게 이동 중 연료가 떨어진 경우 실패
					fuel = -1;
					break;
    			}
    			bfsGoal(Coor.Row, Coor.Col, passMat[Coor.Row][Coor.Col].Row, passMat[Coor.Row][Coor.Col].Col);
    			if(fuel < 0) {	// 도착지 이동 중 연료가 떨어진 경우
					fuel = -1;
					break;
    			}
    			
    			if(passengers == 0)
    				break;
    			que.add(new Coordinate(passMat[Coor.Row][Coor.Col].Row, passMat[Coor.Row][Coor.Col].Col, 0));
    			visit = new boolean[N][N];
    			passMat[Coor.Row][Coor.Col] = null;
    			continue;
    		}
    		
    		for(int i=0 ; i<4 ; ++i) {
    			que.add(new Coordinate(Coor.Row+dr[i], Coor.Col+dc[i], Coor.Dist+1));
    		}
    	}
    	
    	if(fuel > 0 && passengers > 0)
    		fuel = -1;
    }

	public static void bfsGoal(int row, int col, int goalRow, int goalCol) {
		Queue<Coordinate> que = new LinkedList<Coordinate>();
    	boolean[][] visit = new boolean[N][N];
    	que.add(new Coordinate(row, col, 0));
    	
    	while(!que.isEmpty()) {
    		Coordinate Coor = que.poll();
    		if(Coor.Row == goalRow && Coor.Col == goalCol) {	// 목표지점 도착 시 탐색종료
    			fuel -= Coor.Dist;	// 거리 만큼 연료 빼주기
    			if(fuel >= 0) {		// 연료가 남아있다면 승객을 태워서 이동한 거리*2 만큼 연료 충전
    				fuel += Coor.Dist*2;
        			--passengers;
    			}
    			break;
    		}
    		
    		if(Coor.Row < 0 || Coor.Row >= N || Coor.Col < 0 || Coor.Col >= N || mat[Coor.Row][Coor.Col] == 1)	// 범위를 벗어나거나 벽이면 cont
    			continue;
    		else if(visit[Coor.Row][Coor.Col])		// 이미 방문했으면 cont
    			continue;
    		
    		visit[Coor.Row][Coor.Col] = true;
    		
    		for(int i=0 ; i<4 ; ++i) {
    			que.add(new Coordinate(Coor.Row+dr[i], Coor.Col+dc[i], Coor.Dist+1));
    		}
    	}
    }
}
