package adventOfCode;

import java.util.List;

public class Day1 extends Day{

    public Day1(int day, long testResultPart1, long testResultPart2) {
        super(day, testResultPart1, testResultPart2,
                false,
                2
        );
    }

    protected long part1(List<String> lines) {
        int result = 0;
        int currentPosition = 50;

        for(String line: lines) {
            if (line.startsWith("L")) {
                currentPosition = (currentPosition + 100 - Integer.parseInt(line.substring(1))) % 100;
            } else if (line.startsWith("R")) {
                currentPosition = (currentPosition + Integer.parseInt(line.substring(1))) % 100;
            }
            if (currentPosition == 0) {
                result++;
            }
        }

        return result;
    }

    protected long part2(List<String> lines) {
        int result = 0;
        int currentPosition = 50;

        for(String line: lines) {
            print("---------------------------");
            int number = Integer.parseInt(line.substring(1));
            print("" + number);
            result = result + number / 100;
            if (number / 100 > 0) {
                print("overkill " + number / 100);
            }
            if (line.startsWith("L")) {
                int newPosition = currentPosition - (number % 100);
                if (newPosition <= 0 && currentPosition != 0) {
                    result++;
                    print("- over 0");
                }
                currentPosition = (newPosition + 100) % 100;
            } else if (line.startsWith("R")) {
                int newPosition = currentPosition + (number % 100);
                if (newPosition >= 100) {
                    result++;
                    print("+ over 0");
                }
                currentPosition = newPosition % 100;
            }
            print("newPosition " + currentPosition);
        }

        return result;
    }
}
