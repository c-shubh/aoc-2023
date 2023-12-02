import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day2Part1 {
    public static HashMap<String, Integer> bag = new HashMap<>();
    static {
        bag.put("red", 12);
        bag.put("green", 13);
        bag.put("blue", 14);
    }

    public static boolean isPossible(int gameNo, ArrayList<HashMap<String, Integer>> gameDetails) {
        for (HashMap<String, Integer> b : gameDetails) {
            for (Map.Entry<String, Integer> c : b.entrySet()) {
                if (c.getValue().compareTo(bag.get(c.getKey())) <= 0)
                    continue;
                else
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sumOfIds = 0;
        while (sc.hasNextLine()) {
            String game = sc.nextLine();
            String[] gameSplit = game.split(": ");
            String gameNo = gameSplit[0].split(" ")[1];
            String[] cubes = gameSplit[1].split(";");
            ArrayList<HashMap<String, Integer>> gameDetails = new ArrayList<>();
            for (String cube : cubes) {
                HashMap<String, Integer> hm = new HashMap<>();
                for (String bag : cube.split(",")) {
                    String[] bagSplit = bag.strip().split(" ");
                    hm.put(bagSplit[1], Integer.parseInt(bagSplit[0]));
                }
                gameDetails.add(hm);
            }
            int gameId = Integer.parseInt(gameNo);
            if (isPossible(gameId, gameDetails)) {
                sumOfIds += gameId;
            }
        }
        System.out.println(sumOfIds);
    }
}