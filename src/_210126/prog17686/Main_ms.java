package _210126.prog17686;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main_ms {
    public static String[] solution(String[] files) {
        String[] answer = new String[files.length];

        ArrayList<File> list = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            list.add(new File(files[i].toUpperCase(), i));
        }

        Comparator comp = new Comparator<File>() {

            @Override
            public int compare(File f1, File f2) {
                StringBuffer f1_head = makeHead(f1);
                StringBuffer f2_head = makeHead(f2);

                int f1_idx = f1_head.length(), f2_idx = f2_head.length();

                // 아래 ①과 ②는 문자열 HEAD의 사전순 정렬을 위한 리턴 값 설정입니다.
                if (f1_head.toString().compareTo(f2_head.toString()) > 0) return 1; // ①
                else if (f1_head.toString().compareTo(f2_head.toString()) < 0) return -1; // ②

                // 두 파일의 HEAD가 같으면,
                else {
                    int f1_number = makeNumber(f1, f1_idx);
                    int f2_number = makeNumber(f2, f2_idx);

                    // 아래 ①과 ②는 NUMBER의 오름차순 정렬을 위한 리턴 값 설정입니다.
                    if (f1_number > f2_number)  return 1; // ①
                    else if (f1_number < f2_number)  return -1; // ②

                    else return 0; // NUMBER가 같으면 둘의 위치 순서를 바꾸지 않습니다. 위치 그대로 지키고 다음 쌍에 대해 정렬을 진행합니다.
                }
            }
        };

        Collections.sort(list, comp);

        // 정렬된 파일들로부터 원본 파일명을 가져옵니다.
        // 이전에 리스트에 파일명 넣을 때, 다 대문자로 설정해서 인덱스를 따로 담는 클래스 File이 필요했습니다.
        // 인덱스 통해 원본 문자열을 찾았습니다.
        for (int i = 0; i < list.size(); i++) {
            File file = list.get(i);
            answer[i] = files[file.index];
        }

        return answer;
    }

    // 파일명에서 HEAD 를 걸러서 리턴하는 함수입니다.
    private static StringBuffer makeHead(File file) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < file.fileName.length(); i++) {
            char c = file.fileName.charAt(i);
            if (Character.isDigit(c)) break;
            sb.append(c);
        }

        return sb;
    }

    // 파일명에서 NUMBER 를 걸러서 리턴하는 함수입니다.
    private static int makeNumber(File file, int index) {
        StringBuffer sb = new StringBuffer();

        while (index < file.fileName.length() && Character.isDigit(file.fileName.charAt(index))) {
            sb.append(file.fileName.charAt(index));
            index++;
        }

        return Integer.parseInt(sb.toString());
    }

    private static class File {
        String fileName;
        int index;

        public File(String fileName, int index) {
            this.fileName = fileName;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
//        solution(new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"});
//        solution(new String[]{"img-.0005.png", "im.g-5.png", "img.- 3.jpg", "im.g- 001.png"});
    }
}
