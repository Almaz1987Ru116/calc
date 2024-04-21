import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final LinkedHashMap<String, Integer> romanToArab=new LinkedHashMap<>();
    static{
        romanToArab.put("M",1000);
        romanToArab.put("CM",900);
        romanToArab.put("D",500);
        romanToArab.put("CD",400);
        romanToArab.put("C",100);
        romanToArab.put("XC",90);
        romanToArab.put("L",50);
        romanToArab.put("XL",40);
        romanToArab.put("X",10);
        romanToArab.put("IX",9);
        romanToArab.put("V",5);
        romanToArab.put("IV",4);
        romanToArab.put("I",1);
    }
    public static boolean isRoman(String input){
     return romanToArab.containsKey(Character.toString(input.charAt(0)));
    }

    public static String toRoman(int arab){
        String result = "";
        while (arab != 0) {
            for (String key: romanToArab.keySet()) {
                int value = romanToArab.get(key);
                if (arab >= value) {
                    arab -= value;
                    result += key;
                    break;
                }
            }
        }
        return result;
    }


    public static int toArab(String roman){
        int result=0;
        int pred=0;
        for (int i = roman.length()-1;i>=0;i--){
            int chislo=romanToArab.get(Character.toString(roman.charAt(i)));
            if (chislo<pred){
                result-=chislo;
            }
            else {
                result+=chislo;
            }
            pred=chislo;
        }
        return result;
    }
    public static String calc(String input) throws Exception {
        String[] chasti = input.split(" ");
        int result = 0;
        int a,b;
        boolean roman = false;
        if (isRoman(chasti[0]) && isRoman(chasti[2])){
            a=toArab(chasti[0]);
            b=toArab(chasti[2]);
            roman = true;
        }
        else {
            a = Integer.parseInt(chasti[0]);
            b = Integer.parseInt(chasti[2]);
        }
        if (chasti[1].equals("+")){
            result = a+b;
        } else if (chasti[1].equals("-")){
            result = a-b;
        } else if (chasti[1].equals("*")){
            result=a*b;
        }else if (chasti[1].equals("/")){
            result=a/b;
        }

        if (roman) {
            if (result <= 0) {
                throw new Exception("В римской СС нет таких чисел.");
            }
            return  toRoman(result);
        }
        return Integer.toString(result);
    }
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        String example = in.nextLine();
        System.out.println(calc(example));
//        System.out.println(calc("I - II"));
    }
}