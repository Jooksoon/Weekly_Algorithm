import java.util.*;

class Solution_jongsu {
    public static int check(int n, List<List<Integer>> map, int[] pass) {
        int answer = 0;

        boolean[] check = new boolean[n+1];

        for(int i=1; i<=n; i++) {

            if(!check[i]) {
                int cnt = 1;
                Queue<Integer> queue = new LinkedList<>();
                check[i] = true;
                queue.offer(i);

                while(!queue.isEmpty()) {
                    int size = queue.size();

                    for(int j=0; j<size; j++) {
                        int num = queue.poll();
                        List<Integer> list = map.get(num);

                        for(int k=0; k<list.size(); k++) {
                            int fromNum = list.get(k);
                            if( (num==pass[0] && fromNum==pass[1]) || (num==pass[1] && fromNum==pass[0]) )
                                continue;

                            if(!check[fromNum]) {
                                check[fromNum] = true;
                                cnt++;
                                queue.offer(fromNum);
                            }
                        }
                    }
                }

                if(answer==0)
                    answer = cnt;
                else
                    answer -= cnt;
            }
        }

        return answer;
    }

    public int solution(int n, int[][] wires) {

        int answer = Integer.MAX_VALUE;
        List<List<Integer>> map = new ArrayList<>();

        for(int i=0; i<=n; i++) {
            map.add(new ArrayList<>());
        }

        // 초기화
        for(int i=0; i<wires.length; i++) {
            int from = wires[i][0];
            int to = wires[i][1];

            map.get(from).add(to);
            map.get(to).add(from);
        }

        for(int i=0; i<wires.length; i++) {
            int temp = check(n,map, wires[i]);

            answer = Math.min(Math.abs(temp), answer);
        }

        return answer;
    }
}