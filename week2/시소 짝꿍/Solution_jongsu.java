import java.util.*;


/*
입차 - 출차 기록 -> 차량별 주차요금 계산

조건
    입차 후 출차 기록 x => 23:59에 출차한 것으로 간주
    기본 시간 이하 -> 기본 요금 청구
    안 나누어 떨어짐 -> 올림

입력
    feeds[] -> 기본 시간(분), 기본 요금(원), 단위 시간(분), 단위 요금(원)
    records
        시각-차량번호-내역 순(공백으로 구분)
        시각 - HH:MM
        차량번호 - 길이 4 문자열
        내역 - IN 아니면 OUT

출력
    차량 번호가 작은 자동차 순 - 청구할 주차 요금을 차례대로 정수 배열에 담아 return

푸는 방법
    Map1 -> 차량번호-주차장 이용 시간 저장
        Map2 -> 차량 번호

    차량 번호 정렬
    주차장 이용 시간 -> 비용 계산



*/
class Solution_jongsu{
    public int[] solution(int[] fees, String[] records) {

        int basicTime = fees[0];
        int basicMoney = fees[1];
        int additionalTime = fees[2];
        int additionalMoney = fees[3];

        Map<String, String> parkingTime = new HashMap<>(); // 주차 시작한 시간 저장
        Map<String, Integer> totalTime = new HashMap<>(); // 총 주차 시간

        for(String s : records) {
            String[] tempS = s.split(" ");
            // [0] = 시간 / [1] = 차량번호 / [2] = IN 아니면 아웃

            if(parkingTime.containsKey(tempS[1])) { // 안에 주차되어 있는 경우
                String[] startTime = parkingTime.get(tempS[1]).split(":");
                String[] endTime = tempS[0].split(":");

                int time = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1])
                        - Integer.parseInt(startTime[0])*60 - Integer.parseInt(startTime[1]);

                totalTime.put(tempS[1], totalTime.getOrDefault(tempS[1],0) +time);
                parkingTime.remove(tempS[1]);
            } else {
                parkingTime.put(tempS[1], tempS[0]);
            }
        }

        // 아직 안나간 차 23:59출차 처리
        for(String temp : parkingTime.keySet()) {
            int finish = 23*60 + 59;

            String[] startTime = parkingTime.get(temp).split(":");
            totalTime.put(temp, totalTime.getOrDefault(temp, 0) + finish
                    - Integer.parseInt(startTime[0])*60 - Integer.parseInt(startTime[1]));
        }

        for(String s : totalTime.keySet()) {
            int time = totalTime.get(s); // 총 주차 시간
            int money = 0;

            time-= basicTime;
            money += basicMoney;
            // 10 11 12 13 =  20
            if(time > 0) {
                if(time % additionalTime == 0) {
                    money += time/additionalTime * additionalMoney;
                } else {
                    money += (time/additionalTime+1) * additionalMoney;
                }
            }

            totalTime.put(s, money);
        }

        List<String> sorted = new ArrayList<>(totalTime.keySet());
        Collections.sort(sorted);


        int[] answer = new int[sorted.size()];

        for(int i=0; i<sorted.size(); i++) {
            answer[i] = totalTime.get(sorted.get(i));
        }

        return answer;
    }

}