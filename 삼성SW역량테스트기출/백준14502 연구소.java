import java.io.*;
import java.util.*;

public class Main {
    public static int N,M,MAX;
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};
    public static int[][] tmp;
    public static ArrayList<Virus> list;

    public static class Virus {
        public int row, col;
        Virus(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);
        int[][] matrix = new int[N][M];
        list = new ArrayList<>();

        for(int i=0 ; i<N ; ++i) {
            String[] arr = br.readLine().split(" ");
            for(int j=0 ; j<M ; ++j) {
                int val = Integer.parseInt(arr[j]);
                matrix[i][j] = val;
                if(val == 2)
                    list.add(new Virus(i, j));
            }
        }

        MAX = 0;
        bfs(0, 0, 0, matrix);
        System.out.println(MAX);
    }

    public static void bfs(int row, int col, int wall, int[][] mat) {
        if(wall == 3) {		// 벽을 3개 모두 세운 경우
            MAX = Math.max(MAX, checkSafe(mat));
//            System.out.println("=================");
//            for(int[] arr : mat)
//                System.out.println(Arrays.toString(arr));
//            System.out.println("=================");
            return ;
        }
        else if(col == M) {
            row++;
            col = 0;
        }

        for(int r=row ; r<N ; ++r) {
            for(int c=col ; c<M ; ++c) {
                if(mat[r][c] == 0) {
                    mat[r][c] = 1;
                    bfs(r, c+1, wall+1, mat);
                    mat[r][c] = 0;
                }
            }
            col=0;
        }
    }

    public static int checkSafe(int[][] mat) {    // virus 증식 후 안전지대 개수 확인
        int count = 0;
        tmp = new int[N][M];
        for(int i=0 ; i<N ; ++i) {
            for(int j=0 ; j<M ; ++j) {
                tmp[i][j] = mat[i][j];
            }
        }

        for(Virus v : list) {
            spreadVirus(true, v.row, v.col);
        }

        for(int[] arr : tmp) {
            for(int i : arr) {    
                if(i == 0)
                    count++;
            }
        }

        return count;
    }

    public static void spreadVirus(boolean first, int row, int col) {    // virus 증식
        if(row < 0 || row >= N || col < 0 || col >= M)
            return ;
        else if(!first && tmp[row][col] != 0)
            return ;

        if(tmp[row][col] == 0)
            tmp[row][col] = 2;

        first = false;

        for(int i=0 ; i<4 ; ++i) {
            spreadVirus(first, row+dr[i], col+dc[i]);
        }

    }
}

