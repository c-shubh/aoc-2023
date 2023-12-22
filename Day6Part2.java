import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

public class Day6Part2 {

    public static long d(long n, long t) {
        return t * (n - t);
    }

    public static long noOfWays(long time, BigInteger dist) {
        long count = 0;
        long i = 0;
        long end = time / 2;
        for (; i <= end; i++) {
            long myDist = d(time, i);
            if (dist.compareTo(BigInteger.valueOf(myDist)) < 0) {
                System.out.printf("i = %d, d = %d\n", i, myDist);
                break;
            }
        }
        count = end - i + 1;
        count *= 2;
        if (time % 2 == 0)
            count--;
        return count;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        String time = sc.nextLine().replaceAll("\s+", "").substring("Time:".length());
        System.out.println(time);
        String distance = sc.nextLine().replaceAll("\s+", "").substring("Distance:".length());
        System.out.println(distance);
        long t = Integer.parseInt(time);
        BigInteger d = new BigInteger(distance);
        long now = noOfWays(t, d);

        System.out.println(now);
    }
}