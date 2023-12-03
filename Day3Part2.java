import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day3Part2 {
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
            return -1;
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
            return -1;
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
                if (curr == '*') {
                    int numAdj = 0;
                    // check 8 places for digit
                    int[][] coords = new int[][] {
                            // up
                            { j, i - 1 },
                            // down
                            { j, i + 1 },
                            // left
                            { j - 1, i },
                            // right
                            { j + 1, i },
                            // top left
                            { j - 1, i - 1 },
                            // top right
                            { j + 1, i - 1 },
                            // bottom left
                            { j - 1, i + 1 },
                            // bottom right
                            { j + 1, i + 1 } };
                    int[] res = new int[coords.length];

                    for (int k = 0; k < coords.length; k++) {
                        res[k] = extractPartNumber(lines, coords[k][0], coords[k][1]);
                        if (res[k] != -1) {
                            numAdj++;
                        }
                    }

                    // curr is a gear
                    if (numAdj == 2) {
                        int gearRatio = 1;
                        for (int partNo : res) {
                            if (partNo != -1)
                                gearRatio *= partNo;
                        }
                        total += gearRatio;
                    }
                }
            }
        }
        System.out.println(total);
    }
}
