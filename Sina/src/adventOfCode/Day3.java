package adventOfCode;

import java.util.Arrays;
import java.util.List;

public class Day3 extends Day{

    public Day3(int day, long testResultPart1, long testResultPart2) {
        super(day, testResultPart1, testResultPart2,
                false,
                2
        );
    }

    protected long part1(List<String> lines) {
        int result = 0;

        for(String line: lines) {
            int firstDigit = 0;
            int secondDigit = 0;
            for (int i = 0; i < line.length()-1; i++) {
                int valueAtI = Character.getNumericValue(line.charAt(i));
                if (valueAtI > firstDigit) {
                    firstDigit = valueAtI;
                    secondDigit = 0;
                } else if (valueAtI > secondDigit) {
                    secondDigit = valueAtI;
                }
            }
            int lastCharVal = Character.getNumericValue(line.charAt(line.length()-1));
            if (lastCharVal > secondDigit) {
                secondDigit = lastCharVal;
            }
            result += firstDigit * 10 + secondDigit;
        }

        return result;
    }

    protected long part2(List<String> lines) {
        long result = 0;

        for(String line: lines) {
            int[] digits = {0,0,0,0,0,0,0,0,0,0,0,0};
            for (int i = 0; i < line.length(); i++) {
                int valueAtI = Character.getNumericValue(line.charAt(i));
                int distanceToEnd = line.length() - i - 1;
                boolean valueUsed = false;
                for (int j = 0; j < 12; j++) {
                    if (valueUsed) {
                        digits[j] = 0;
                    } else if (distanceToEnd >= (11-j) && valueAtI > digits[j]) {
                        digits[j] = valueAtI;
                        valueUsed = true;
                    }
                }
                System.out.println(Arrays.toString(digits));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            long finalNumber = 0;
            for (int i = 0; i < 12 ; i++) {
                finalNumber *= 10;
                finalNumber += digits[i];
            }
            result += finalNumber;
        }

        return result;
    }
}
