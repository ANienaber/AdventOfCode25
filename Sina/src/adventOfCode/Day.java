package adventOfCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public abstract class Day {
    private final int day;
    private final boolean test;
    private final long testResultPart1;
    private final long testResultPart2;
    private final int part;

    protected Day(int day, long testResultPart1, long testResultPart2, boolean test, int part) {
        this.day = day;
        this.testResultPart1 = testResultPart1;
        this.testResultPart2 = testResultPart2;
        this.test = test;
        this.part = part;
    }

    public void run () {
        if (part == 1) {
            long result = part1(read());
            System.out.println(result);
            if (test) {
                System.out.println(result == testResultPart1 ? "Test successful" : "Test unsuccessful");
            }
        } else {
            long result = part2(read());
            System.out.println(result);
            if (test) {
                System.out.println(result == testResultPart2 ? "Test successful" : "Test unsuccessful");
            }
        }
    }

    abstract long part1(List<String> lines);

    abstract long part2(List<String> lines);

    protected List<String> read() {
        try(BufferedReader br = new BufferedReader(new FileReader(new File(Objects.requireNonNull(Day.class.getResource("/day" + day + (test ? "_test" : "") + ".txt")).toURI())))) {
            String line = br.readLine();
            List<String> lines = new ArrayList<>();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
            return lines;
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void print(String value) {
        if (test) {
            System.out.println(value);
        }
    }
}
