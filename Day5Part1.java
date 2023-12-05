import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Day5Part1 {
    static class Range {
        long start;
        long end;

        public Range(long start, long length) {
            this.start = start;
            this.end = start + length - 1;
        }

        public boolean contains(long val) {
            return start <= val && val <= end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Range))
                return false;
            Range otherRange = (Range) other;
            return this.start == otherRange.start && this.end == otherRange.end;
        }

        @Override
        public String toString() {
            return "Range [start=" + start + ", end=" + end + "]";
        }

    }

    static class Mapping {
        String name;
        HashMap<Range, Range> rangeMap = new HashMap<>();

        public Mapping(String name) {
            this.name = name;
        }

        public Mapping(String name, HashMap<Range, Range> rangeMap) {
            this.name = name;
            this.rangeMap = rangeMap;
        }

        @Override
        public String toString() {
            return "Mapping [name=" + name + ", rangeMap=" + rangeMap + "]";
        }
    }

    static LinkedHashMap<String, Mapping> mappingMap = new LinkedHashMap<>();

    /** assumes that `sourceValue` is present in `source` range */
    public static long srcToDestMap(Range source, Range destination, long sourceValue) {
        long offset = sourceValue - source.start;
        return destination.start + offset;
    }

    public static long categoryMapping(Mapping m, long sourceValue) {
        // TODO: this is doing linear search, can make it faster by sorting the
        // ranges in the constructor and using binary search
        for (Map.Entry<Range, Range> entry : m.rangeMap.entrySet()) {
            if (entry.getKey().contains(sourceValue)) {
                return srcToDestMap(entry.getKey(), entry.getValue(), sourceValue);
            }
        }
        return sourceValue;
    }

    public static long findLocation(long seed) {
        for (Map.Entry<String, Mapping> entry : mappingMap.entrySet()) {
            seed = categoryMapping(entry.getValue(), seed);
        }
        return seed;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Long> seeds = new ArrayList<>();

        Mapping mapping = null;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            if (line.strip().length() == 0) { // empty line
                continue;
            }
            if (Character.isDigit(line.charAt(0))) { // range
                String[] lineSplit = line.split(" ");
                long destRangeStart = Long.parseLong(lineSplit[0].strip());
                long srcRangeStart = Long.parseLong(lineSplit[1].strip());
                long rangeLength = Long.parseLong(lineSplit[2].strip());

                Range srcRange = new Range(srcRangeStart, rangeLength);
                Range destRange = new Range(destRangeStart, rangeLength);

                mapping.rangeMap.put(srcRange, destRange);

            } else if (line.contains("-to-")) { // src-to-dest map
                String[] lineSplit = line.split(Pattern.quote("-to-"));
                String srcName = lineSplit[0].strip();
                String destName = lineSplit[1].split(" ")[0].strip();

                mapping = new Mapping(srcName + "2" + destName);
                mappingMap.put(mapping.name, mapping);

            } else if (line.startsWith("seeds: ")) { // list of seeds
                String[] strNums = line.replace("seeds: ", "").strip().split(" ");
                for (String strNum : strNums) {
                    seeds.add(Long.parseLong(strNum));
                }
            }
        }

        ArrayList<Long> locations = new ArrayList<>();
        for (long seed : seeds) {
            locations.add(findLocation(seed));
        }
        System.out.println(Collections.min(locations));
    }
}
