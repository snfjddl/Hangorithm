class Solution {
    public void setZeroes(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        boolean[] zeroRow = new boolean[N];
        boolean[] zeroCol = new boolean[M];
        boolean[][] visit = new boolean[N][M];
        
        for(int i=0 ; i<N ; ++i) {
            for(int j=0 ; j<M ; ++j) {
                if(matrix[i][j] == 0) visit[i][j] = true;
            }
        }
        
        for(int i=0 ; i<N ; ++i) {
            for(int j=0 ; j<M ; ++j) {
                if(matrix[i][j] == 0 && visit[i][j]) {
                    int r=i, R=i, c=j, C=j;
                    if(!zeroRow[i]) {
                        while(c>=0) {
                            matrix[i][c--] = 0;
                        }
                        while(C<M) {
                            matrix[i][C++] = 00;
                        }    
                    }
                    
                    if(!zeroCol[j]) {
                        while(r>=0) {
                            matrix[r--][j] = 0;
                        }
                        while(R<N) {
                            matrix[R++][j] = 0;
                        }    
                    }
                    
                    zeroRow[i] = true;
                    zeroCol[j] = true;
                }
            }
        }
    }
}