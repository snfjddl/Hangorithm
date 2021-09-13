import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int[] A = new int[N];
    	int idx=0;
    	for(String str : br.readLine().split(" ")) {
    		A[idx++] = Integer.parseInt(str);
    	}
    	String[] tmp = br.readLine().split(" ");
    	int B = Integer.parseInt(tmp[0]);
    	int C = Integer.parseInt(tmp[1]);
    	
    	long Bcnt=0, Ccnt=0;
		for(int i : A) {
			int rest = i-B;
			Bcnt+=1;
			if(rest > 0) {
				Ccnt += rest/C;
				if(rest%C != 0)
					Ccnt++;
			}
		}
    	
    	System.out.println(Bcnt+Ccnt);
    }
}

		