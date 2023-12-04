import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Day4Part1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineSplit = line.split(": +");
            String cardNum = lineSplit[0];
            String cardData = lineSplit[1];
            String[] cardDataSplit = cardData.split(Pattern.quote("|"));

            HashSet<Integer> winningNums = new HashSet<>();
            for (String num : cardDataSplit[0].split(" +")) {
                String s = num.strip();
                if (s.isEmpty())
                    continue;
                int n = Integer.parseInt(s);
                winningNums.add(n);
            }

            int cardTotal = 0;
            for (String num : cardDataSplit[1].split(" +")) {
                String s = num.strip();
                if (s.isEmpty())
                    continue;
                int n = Integer.parseInt(s);
                if (winningNums.contains(n)) {
                    // first match
                    if (cardTotal == 0) {
                        cardTotal = 1;
                    } else {
                        cardTotal *= 2;
                    }
                }
            }
            total += cardTotal;
        }
        System.out.println(total);
    }

}