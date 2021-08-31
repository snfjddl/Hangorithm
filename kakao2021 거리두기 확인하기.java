import java.util.*;

class Solution {
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};
    
    static class Node {
        public int x, y, dist;
        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    public int[] solution(String[][] places) {
        char[][] mat;
        Queue<Node> que;
        Queue<Node> pos;
        int[] answer = new int[places.length];
        
        for(int i=0 ; i<places.length ; ++i) {
            que = new LinkedList<Node>();
            pos = new LinkedList<Node>();
            mat = new char[5][5];
            for(int j=0 ; j<5 ; ++j) {
                mat[j] = places[i][j].toCharArray();
                for(int k=0 ; k<5 ; ++k) {
                    if(mat[j][k] == 'P') {
                        pos.add(new Node(j, k, 0));
                    }
                }
            }
            int ans=1;
            while(!pos.isEmpty()) {
                boolean[][] visit = new boolean[5][5];
                que.add(pos.poll());
                if(BFS(mat, que, visit) == 0) {
                    ans = 0;
                    break;
                }
            }
            answer[i] = ans;
        }
        
        return answer;
    }
    
    public static int BFS(char[][] mat, Queue<Node> que, boolean[][] visit) {
        while(!que.isEmpty()) {
            Node node = que.poll();
            int x = node.x;
            int y = node.y;
            int dist = node.dist;
                  
            if(x < 0 || y < 0 || x >= 5 || y >= 5
              || dist == 3) {
                continue;
            }
            else if(visit[x][y]) {
                continue;
            }
            visit[x][y] = true;
            
            if(dist > 0) {
                switch(mat[x][y]) {
                    case 'P':
                        return 0;
                    case 'X':
                        continue;
                }
            }
            
            for(int i=0 ; i<4 ; ++i) {
                que.add(new Node(x+dr[i], y+dc[i], dist+1));
            }
        }
        
        return 1;
    }
}