package utils;

import java.util.Scanner;

import static jdk.nashorn.internal.runtime.ScriptingFunctions.readLine;

public class ConvertUtils {
    public static Scanner input = new Scanner(System.in);

//    parseIn
    public static int convertParseInt() {
        int result;
        do {
            try {
                result = Integer.parseInt(input.nextLine());
                return result;
            } catch (Exception e) {
                System.out.println("Nhập sai vui lòng nhập lại");
                System.out.println("⟹");
            }
        } while (true);
    }
}
