import java.util.*;

/*
피로도 시스템(0이상의 정수)
일정 피로도를 사용 -> 던전 탐험 가능
각 던전 탐험 시작 위한 최소 피로도, 던전 탐험 마쳤을 때 소모되는 소모 피로도
최소 피로도 = 해당 던전 탐험을 하기 위해 가지고 있어야 하는 최소한의 피로도
소모 피로도 = 던전 탐험 후 소모되는 피로도

하루에 한 번 탐험 가능한 던전 여러개
이 던전들을 최대한 많이 탐험

최대 던전 수 return

k = 현재 피로도 = 1~5천 이하 자연수
dungeons = 최소필요피로도, 소모 피로도 = 각각 1~1천


*/

public class Solution_jongsu {

    static int max = 0;
    static boolean[] check;

    // dungeons[0] = 최소 피로도, dungeons[1] = 소모 피로도
    public void DFS(int k, int count, int[][] dungeons) {

        for(int i=0; i<dungeons.length; i++) {

            if(check[i] || dungeons[i][0] > k) {
                max = Math.max(max, count);
                continue;
            }

            check[i] = true;
            DFS(k-dungeons[i][1], count+1, dungeons);
            check[i] = false;
        }

    }

    public int solution(int k, int[][] dungeons) {

        check = new boolean[dungeons.length];

        DFS(k, 0, dungeons);

        return max;
    }
}
