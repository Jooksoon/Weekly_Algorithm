import java.util.*;
import java.io.*;

/*
캐시 크기 얼마나?

#입력
캐시크기 - cacheSize - 0~30
도시이름 배열 - cities - 최대 10만
대소문자 구분x

#출력
입력된 도시이름 배열 순서대로 처리 -> 총 실행시간 출력

#조건
LRU 알고리즘 사용
cache hit = 실행시간 1
cache miss = 실행시간 5

캐시 안에 도시 o -> 도시 맨 앞에 꺼내기
캐시 안에 도시 x -> 맨 뒤 도시 꺼내기, 도시 앞에 넣기
*/
class Solution_jongsu {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        List<String> cache = new LinkedList<>();

        for(int i=0; i<cities.length; i++) {
            String city = cities[i].toLowerCase();
            if(cacheSize > cache.size()) { // 1. cache가 덜 찼다면
                if(cache.contains(city)) { // 1.1 이미 캐시 안에 있다면
                    answer+=1;
                    cache.add(0, city);
                    for(int j=1; j<cache.size(); j++) {
                        if(cache.get(j).equals(city)) {
                            cache.remove(j);
                        }
                    }
                } else { // 1.2 캐시 안에 없다면
                    answer+=5;
                    cache.add(0,city);
                }
            } else { // 2. 캐시가 다 찼다면
                if(cache.contains(city)) { // 2.1 이미 캐시 안에 도시가 들어있음
                    answer+=1;
                    cache.add(0, city);
                    for(int j=1; j<cache.size(); j++) {
                        if(cache.get(j).equals(city)) {
                            cache.remove(j);
                        }
                    }
                } else { // 2.2 캐시 안에 도시 없음 -> 가장 옛날 도시 제거, 새 도시 앞에 추가
                    answer+=5;
                    cache.add(0, city);
                    cache.remove(cache.size()-1);
                }
            }
        }

        return answer;
    }
}