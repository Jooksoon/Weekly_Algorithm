import java.util.*;

public class Solution_jongsu {

    static class Point {

        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int[][] maps) {

        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};

        int N = maps.length;
        int M = maps[0].length;

        int answer = 1;
        int[][] check = new int[N][M];
        int finalX = N-1;
        int finalY = M-1;

        Point p = new Point(0,0);

        Queue<Point> queue = new LinkedList<>();
        queue.offer(p);
        check[0][0] = 1;

        while(!queue.isEmpty() && check[finalX][finalY] == 0) {

            int size = queue.size();
            for(int i=0; i<size; i++) {

                Point temp = queue.poll();

                for(int j=0; j<4; j++) {
                    int tx = temp.x + dx[j];
                    int ty = temp.y + dy[j];

                    if(tx >= 0 && ty >= 0 && tx < N && ty < M) {
                        if(maps[tx][ty] == 1 && check[tx][ty] == 0) {
                            check[tx][ty] = check[temp.x][temp.y]+1;
                            // if(tx == finalX && ty == finalY) {
                            //     break;
                            // }
                            queue.offer(new Point(tx,ty));
                        }
                    }
                }

            }

        }

        if(check[finalX][finalY] == 0)
            return -1;

        return check[finalX][finalY];
    }
}
