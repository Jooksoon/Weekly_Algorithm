import java.util.*;

/*
새로운 메뉴 구성
단품을 조합해서 코스메뉴
요리 개수별 최대로 주문된 개수

orders 배열 크기 - 2~20
orders 배열 각 원소 크기 - 2~10길이 문자열(대문자만, 중복x)
course 배열 길이 1~10

#문제풀이

2번 이상 나온 단품 세트 모두 저장
course에 있으면서 숫자 더큰거 저장



*/
class Solution_jongsu {

    public static String[] solution(String[] orders, int[] course) {

        Map<String, Integer> result = new HashMap<>();
        List<Set<Character>> setList = new ArrayList<>();

        int[] count = new int['Z'-'A'+1];

        for(int i=0; i<orders.length; i++) {
            setList.add(new HashSet<Character>());
            String temp = orders[i];
            for(int j=0; j<temp.length(); j++) {
                setList.get(i).add(temp.charAt(j));
                count[temp.charAt(j)-'A']+=1;
            }

        }

        Queue<String> queue = new LinkedList<>();

        // 2번 이상 나온 알파벳 찾기
        List<Character> base = new ArrayList<>();
        for(int i=0; i<='Z'-'A'; i++) {
            if(count[i] >= 2) {
                queue.offer(""+(char)('A'+i));
                base.add((char)('A'+i));
                // System.out.println(base.get(base.size()-1));
            }
        }


        while(!queue.isEmpty() && queue.peek().length() <= course[course.length-1]+1) {

            int size = queue.size();
            // 해당 문자열 있는지 체크, 원래 있던 세트 + 알파벳 달기
            for(int i=0; i<size; i++) {
                String temp = queue.poll();

                for(int j=0; j<base.size(); j++) {
                    if(temp.charAt(temp.length()-1) >= base.get(j)) {
                        continue;
                    }
                    String newString = temp+base.get(j);

                    int frequent = 0;
                    boolean isSame = true;
                    for(int k=0; k<setList.size(); k++) {
                        isSame = true;
                        Set<Character> tempSet = setList.get(k);
                        for(int l=0; l<newString.length(); l++) {
                            if(!tempSet.contains(newString.charAt(l))) {
                                isSame = false;
                                break;
                            }
                        }

                        if(isSame)
                            frequent++;
                    }

                    if(frequent >= 2) {
                        // System.out.println(newString);
                        result.put(newString, frequent);
                        queue.offer(newString);
                    }
                }
            }

        }

        Map<Integer, ArrayList<String>> realResult = new HashMap<>();
        Set<Integer> newCourse = new HashSet<>();
        for(int i=0; i<course.length; i++) {
            newCourse.add(course[i]);
            realResult.put(course[i], new ArrayList<String>());
        }

        for(String key : result.keySet()) {
            int tempLength = key.length();

            if(newCourse.contains(tempLength)) {
                List<String> tempList = realResult.get(tempLength);
                if(tempList.size() == 0 || result.get(tempList.get(0)) < result.get(key)) { // 더 많이 나왔을 때
                    tempList.clear();
                    tempList.add(key);
                } else if(result.get(tempList.get(0))== result.get(key)){
                    tempList.add(key);
                }
            }

        }

        List<String> answer = new ArrayList<>();

        for(int key : realResult.keySet()) {
            for(String s : realResult.get(key)) {
                answer.add(s);
            }
        }

        Collections.sort(answer);

        return answer.toArray(new String[answer.size()]);
    }
}