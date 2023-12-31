import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day3Part1 {
    static HashSet<String> usedNumbers = new HashSet<>();

    public static boolean isOutOfBounds(ArrayList<?> l, int idx) {
        return (idx < 0 || idx >= l.size());
    }

    public static boolean isOutOfBounds(String s, int idx) {
        return (idx < 0 || idx >= s.length());
    }

    public static int extractPartNumber(ArrayList<String> lines, int x, int y) {
        if (!isOutOfBounds(lines, y)
                && !isOutOfBounds(lines.get(y), x)
                && !Character.isDigit(lines.get(y).charAt(x)))
            return 0;
        String line = lines.get(y);
        int i = x, j = x;
        while (i >= 0 && Character.isDigit(line.charAt(i))) {
            i--;
        }
        i++;

        while (j < line.length() && Character.isDigit(line.charAt(j))) {
            j++;
        }

        String numberLocation = String.join(",", new String[] {
                String.valueOf(y),
                String.valueOf(i),
                String.valueOf(j),
        });
        if (usedNumbers.contains(numberLocation)) {
            return 0;
        }
        usedNumbers.add(numberLocation);
        int num = Integer.parseInt(line.substring(i, j));
        return num;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        int total = 0;

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                char curr = line.charAt(j);

                // find symbol
                if (curr != '.' && !Character.isLetterOrDigit(curr)) {
                    // check 8 places for digit
                    // up
                    total += extractPartNumber(lines, j, i - 1);
                    // down
                    total += extractPartNumber(lines, j, i + 1);
                    // left
                    total += extractPartNumber(lines, j - 1, i);
                    // right
                    total += extractPartNumber(lines, j + 1, i);
                    // top left
                    total += extractPartNumber(lines, j - 1, i - 1);
                    // top right
                    total += extractPartNumber(lines, j + 1, i - 1);
                    // bottom left
                    total += extractPartNumber(lines, j - 1, i + 1);
                    // bottom right
                    total += extractPartNumber(lines, j + 1, i + 1);
                }
            }
        }
        System.out.println(total);
    }
}
