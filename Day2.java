/*
Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
*/

import java.util.Scanner;
import java.util.Map;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Day2 {
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