package homework;

public class Main {
    public static void main(String[] args) {
        // 1
        System.out.println("1. 자바를 이용해 아래 문장을 출력해 보세요.");
        printMyInfo();
        System.out.println();

        // 2
        System.out.println("2. 아래 두 정수값을 저장 한 후 몫과 나머지를 계산한 후에 출력 하시오.");
        printDivAndMod();
        System.out.println();

        // 3.1
        System.out.println("3.1  아래 문장을  string 변수에 저장 후에 모든 'd'를 'f'로 바꾸는 프로그램을 작성하시오.");
        replaceString();
        System.out.println();

        // 3.2
        System.out.println("3.2  아래문장을 string 변수에 저장 후에 인덱스 10 에서 부터 16까지의 sub string 을 다른 변수에 저장 후 출력하시오.");
        substringAndPrint();
        System.out.println();

        // 3.3
        System.out.println("3.3  아래 두 문장을 string변수에 저장 후에 이 두 문장을 하나의 문장으로 합하여 다른 string 변수에 저장 후에 출력 하시오.");
        concatString();
        System.out.println();

        //3.4
        System.out.println("3.4 아래 문장을 string에 저장 한 후 \"and\" 라는 문장이 들어 있는지를 판단하는 코드를 작성하시오.");
        containsWord();
        System.out.println();

        // 3.5
        System.out.println("3.5 아래문장을 string에 저장 후에 0번째 글자와 10번째 글자를 출력하는 코드를 작성하시오.");
        charPrint();
        System.out.println();

        // 4
        System.out.println("4. 아래 실수를 각각 float와  double 변수로 정의 하시오.");
        convertDataType();
        System.out.println();

        // 5
        System.out.println("5. 아래 파이 값을 <상수>로 정의하고, 반지름 길이가 5.4일경우 원의 면적을 계산하는 코드를 작성하시오.");
        areaOfCircle();
    }

    /* 1. 자바를 이용해 아래 문장을 출력해 보세요. */
    private static void printMyInfo() {
        String name = "박대영";
        String major = "컴퓨터공학과";
        int stdNo = 22212029;

        System.out.println(name + "는 " + major + " 소속으로 학번은 " + stdNo + "입니다.");
    }

    /* 2. 아래 두 정수값을 저장 한 후 몫과 나머지를 계산한 후에 출력 하시오. */
    private static void printDivAndMod() {
        int x = 10;
        int y = 3;

        int div = x / y;
        int mod = x % y;
        System.out.println("10을 3으로 나눈 몫은 " + div + "이고 나머지는 " + mod + "입니다.");
    }

    /* 3.1  아래 문장을  string 변수에 저장 후에 모든 'd'를 'f'로 바꾸는 프로그램을 작성하시오. */
    private static void replaceString() {

        String temp = "The quick brown fox jumps over the lazy dog.";

        System.out.println(temp.replaceAll("d", "f"));
    }

    /* 3.2  아래문장을 string 변수에 저장 후에 인덱스 10 에서 부터 16까지의 sub string 을 다른 변수에 저장 후 출력하시오. */
    private static void substringAndPrint() {

        String temp = "The quick brown fox jumps over the lazy dog.";

        String sub = temp.substring(10, 16);
        System.out.println(sub);
    }

    /* 3.3  아래 두 문장을 string변수에 저장 후에 이 두 문장을 하나의 문장으로 합하여 다른 string 변수에 저장 후에 출력 하시오. */
    private static void concatString() {

        String sentence1 = "PHP Exercises and";
        String sentence2 = "Python Exercises";
        String sentence = sentence1.concat(sentence2);

        System.out.println(sentence);
    }

    /* 3.4 아래 문장을 string에 저장 한 후 "and" 라는 문장이 들어 있는지를 판단하는 코드를 작성하시오. */
    private static void containsWord() {

        String temp = "PHP Exercises and Python Exercises";

        System.out.println(temp.contains("and"));
    }

    /* 3.5 아래문장을 string에 저장 후에 0번째 글자와 10번째 글자를 출력하는 코드를 작성하시오. */
    private static void charPrint() {

        String temp = "Java Exercises!";
        System.out.println("0번째: " +  temp.charAt(0) + ", 10번째: " + temp.charAt(10));
    }

    /* 4. 아래 실수를 각각 float와  double 변수로 정의 하시오. */
    private static void convertDataType() {

        float f = 104.5F;
        double d = 104.5;

        System.out.println("정의 완료. float f = 104.5F, double d = 104.5");
    }

    /* 5. 아래 파이 값을 <상수>로 정의하고, 반지름 길이가 5.4일경우 원의 면적을 계산하는 코드를 작성하시오. */
    private static void areaOfCircle() {

        final double PI = 3.141592;
        double r = 5.4;

        System.out.println(r * r * PI);
    }
}
