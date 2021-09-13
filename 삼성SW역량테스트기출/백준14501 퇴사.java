import java.io.*;
import java.util.*;

public class Main {
	public static int N, MAX;
	public static int[] T, P;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	MAX = 0;
    	N = Integer.parseInt(br.readLine());
    	T = new int[N+1];
    	P = new int[N+1];
    	
    	for(int i=1 ; i<=N ; ++i) {
    		String[] tmp = br.readLine().split(" ");
    		T[i] = Integer.parseInt(tmp[0]);
    		P[i] = Integer.parseInt(tmp[1]);
    	}
    	
    	search(1, 0);
    	
    	System.out.print(MAX);
    }
    
    public static void search(int day, int total) {	// day에서 N까지 모든 경우의 수 확인
    	if(day > N) {						// N일 넘어간 경우 return
    		return ;
    	}
    	
    	for(int i=day ; i<=N ; ++i) {
    		if(i+T[i]-1 > N)						// i일의 상담을 받으면 N일이 넘어가는 경우 pass 
    			continue;
    		MAX = Math.max(MAX, total+P[i]);
    		search(i+T[i], total+P[i]);
    	}
    }
}
