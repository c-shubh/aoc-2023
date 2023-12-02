import java.util.Scanner;

public class Day1Part1 {

    public static int calibrationValue(String s) {
        int i = 0, j = s.length() - 1;
        while (Character.isLetter(s.charAt(i))) {
            i++;
        }
        while (Character.isLetter(s.charAt(j))) {
            j--;
        }
        return Integer.parseInt(s.charAt(i) + "") * 10 + Integer.parseInt(s.charAt(j) + "");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalCalibrationValue = 0;
        while (sc.hasNextLine()) {
            totalCalibrationValue += calibrationValue(sc.nextLine());
        }
        System.out.println(totalCalibrationValue);
    }
}