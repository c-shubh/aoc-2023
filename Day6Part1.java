import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day6Part1 {

    public static int d(int n, int t) {
        return t * (n - t);
    }

    public static int noOfWays(int time, int dist) {
        int count = 0;
        for (int i = 0; i <= time / 2; i++) {
            int myDist = d(time, i);
            if (myDist > dist) {
                count++;
                System.out.printf("i = %d, d = %d\n", i, myDist);
            }
        }
        count *= 2;
        if (time % 2 == 0)
            count--;
        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        String[] time = sc.nextLine().split("\s+");
        String[] distance = sc.nextLine().split("\s+");
        int res = 1;
        for (int i = 1; i < time.length; i++) {
            int t = Integer.parseInt(time[i]);
            int d = Integer.parseInt(distance[i]);
            int now = noOfWays(t, d);
            System.out.println(now);
            res *= now;
        }
        System.out.println(res);
    }
}