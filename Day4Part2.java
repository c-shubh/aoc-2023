import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class Day4Part2 {
    static class Card {
        int number;
        HashSet<Integer> winningNums;
        ArrayList<Integer> numsIHave;

        public Card(int number, HashSet<Integer> winningNums, ArrayList<Integer> numsIHave) {
            this.number = number;
            this.winningNums = winningNums;
            this.numsIHave = numsIHave;
        }

        @Override
        public String toString() {
            return number + " : " + numsIHave.toString();
        }
    }

    static TreeMap<Integer, Card> cards = new TreeMap<>();
    static HashMap<Integer, Integer> processedCards = new HashMap<>();

    public static int countMatches(Card card) {
        int matches = 0;
        for (int n : card.numsIHave) {
            if (card.winningNums.contains(n)) {
                matches++;
            }
        }
        return matches;
    }

    public static int processCard(Card card) {
        if (processedCards.containsKey(card.number))
            return processedCards.get(card.number);

        int matches = countMatches(card);
        int total = matches;
        for (int i = 1; i <= matches; i++) {
            Card toProcess = cards.get(card.number + i);
            total += processCard(toProcess);
        }
        processedCards.put(card.number, total);
        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = 0;
        // get all cards
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] lineSplit = line.split(": +");
            int cardNum = Integer.parseInt(lineSplit[0].split(" +")[1]);
            String cardData = lineSplit[1];
            String[] cardDataSplit = cardData.split(Pattern.quote("|"));

            HashSet<Integer> winningNums = new HashSet<>();
            for (String num : cardDataSplit[0].strip().split(" +")) {
                String s = num.strip();
                int n = Integer.parseInt(s);
                winningNums.add(n);
            }
            ArrayList<Integer> numsIHave = new ArrayList<>();
            for (String num : cardDataSplit[1].strip().split(" +")) {
                numsIHave.add(Integer.parseInt(num.strip()));
            }
            Card card = new Card(cardNum, winningNums, numsIHave);
            cards.put(card.number, card);
        }
        // process all cards
        for (Card card : cards.values()) {
            total += processCard(card);
        }
        total += cards.size();
        System.out.println(total);
    }

}