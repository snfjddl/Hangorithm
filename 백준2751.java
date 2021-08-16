import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	boolean[] arr = new boolean[2000001];
    	
    	int N = Integer.parseInt(br.readLine());
    	
    	for(int i=0 ; i<N ; ++i) {
    		int idx = Integer.parseInt(br.readLine()) + 1000000;
    		arr[idx] = true;
    	}
    	
    	for(int i=0 ; i<arr.length ; ++i) {
    		if(arr[i])
    			sb.append(i-1000000).append('\n');
    	}
    	
    	System.out.print(sb.toString());
    }
}