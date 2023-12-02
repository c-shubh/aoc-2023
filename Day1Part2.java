import java.util.Scanner;

public class Day1Part2 {

    public static int firstDigit(String s) {
        int i = 0;
        while (i < s.length() && Character.isLetter(s.charAt(i))) {
            i++;
        }

        int j = 0;
        int jnum = 0;
        loop: for (j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            switch (c) {
                case 'o':
                    if (s.indexOf("one", j) == j) {
                        jnum = 1;
                        break loop;
                    }
                    break;
                case 't':
                    if (s.indexOf("two", j) == j) {
                        jnum = 2;
                        break loop;
                    } else if (s.indexOf("three", j) == j) {
                        jnum = 3;
                        break loop;
                    }
                    break;
                case 'f':
                    if (s.indexOf("four", j) == j) {
                        jnum = 4;
                        break loop;
                    } else if (s.indexOf("five", j) == j) {
                        jnum = 5;
                        break loop;
                    }
                    break;
                case 's':
                    if (s.indexOf("six", j) == j) {
                        jnum = 6;
                        break loop;
                    } else if (s.indexOf("seven", j) == j) {
                        jnum = 7;
                        break loop;
                    }
                    break;
                case 'e':
                    if (s.indexOf("eight", j) == j) {
                        jnum = 8;
                        break loop;
                    }
                    break;
                case 'n':
                    if (s.indexOf("nine", j) == j) {
                        jnum = 9;
                        break loop;
                    }
                    break;
                default:
                    break;
            }
        }
        // check which came first
        if (i < j) {
            return Integer.parseInt(s.charAt(i) + "");
        }
        return jnum;
    }

    public static int lastDigit(String s) {
        int i = s.length() - 1;
        while (i >= 0 && Character.isLetter(s.charAt(i))) {
            i--;
        }

        int j = 0;
        int jnum = 0;
        loop: for (j = s.length() - 1; j >= 0; j--) {
            char c = s.charAt(j);
            switch (c) {
                case 'o':
                    if (s.lastIndexOf("one", j) == j) {
                        jnum = 1;
                        break loop;
                    }
                    break;
                case 't':
                    if (s.lastIndexOf("two", j) == j) {
                        jnum = 2;
                        break loop;
                    } else if (s.lastIndexOf("three", j) == j) {
                        jnum = 3;
                        break loop;
                    }
                    break;
                case 'f':
                    if (s.lastIndexOf("four", j) == j) {
                        jnum = 4;
                        break loop;
                    } else if (s.lastIndexOf("five", j) == j) {
                        jnum = 5;
                        break loop;
                    }
                    break;
                case 's':
                    if (s.lastIndexOf("six", j) == j) {
                        jnum = 6;
                        break loop;
                    } else if (s.lastIndexOf("seven", j) == j) {
                        jnum = 7;
                        break loop;
                    }
                    break;
                case 'e':
                    if (s.lastIndexOf("eight", j) == j) {
                        jnum = 8;
                        break loop;
                    }
                    break;
                case 'n':
                    if (s.lastIndexOf("nine", j) == j) {
                        jnum = 9;
                        break loop;
                    }
                    break;
                default:
                    break;
            }
        }
        // check which came first
        if (i > j) {
            return Integer.parseInt(s.charAt(i) + "");
        }
        return jnum;
    }

    public static int calibrationValue(String s) {
        return firstDigit(s) * 10 + lastDigit(s);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalCalibrationValue = 0;
        while (sc.hasNextLine()) {
            int calVal = calibrationValue(sc.nextLine());
            System.out.println(calVal);
            totalCalibrationValue += calVal;
        }
        System.out.println(totalCalibrationValue);
    }
}