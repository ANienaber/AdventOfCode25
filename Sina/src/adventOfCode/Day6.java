package adventOfCode;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Day6 extends Day{

    public Day6(int day, long testResultPart1, long testResultPart2) {
        super(day, testResultPart1, testResultPart2,
                false,
                2
        );
    }

    protected long part1(List<String> lines) {
        long result = 0;

        List<String[]> arguments = new ArrayList<>();
        String[] operators = new String[1];
        for(String line: lines) {
            String[] lineSplit = line.strip().replaceAll(" +", " ").split(" ");
            if (line.contains("+")) {
                operators = lineSplit;
            } else {
                arguments.add(lineSplit);
            }
        }

        for (int i=0; i<operators.length; i++) {
            if (operators[i].equals("+")){
                for (String[] argument : arguments) {
                    result += Long.parseLong(argument[i]);
                }
            } else if (operators[i].equals("*")){
                long multiResult = 1;
                for (String[] argument : arguments) {
                    multiResult *= Long.parseLong(argument[i]);
                }
                result += multiResult;
            }
        }

        return result;
    }

    protected long part2(List<String> lines) {
        long result = 0;

        int maxlength = 0;
        for (String line : lines) {
            if (line.length() > maxlength) {
                maxlength = line.length();
            }
        }
        for (String line : lines) {
            if (line.length() < maxlength) {
                lines.set(lines.indexOf(line), line.concat(" ".repeat(maxlength-line.length())));
            }
        }

        long multiResult = 1;
        char currentOperator = '+';
        for (int i=0; i<lines.getFirst().length(); i++) {
            long number = 0;
            for (String line : lines) {
                if (!line.contains("+") && line.charAt(i) != ' ') {
                    System.out.println(("number before: " + number + " char: " + line.charAt(i)));
                    number *= 10;
                    number += Character.getNumericValue(line.charAt(i));
                }
            }
            if (number == 0) {
                continue;
            }

            char operator = lines.getLast().charAt(i);
            if (operator != ' ') {
                if (currentOperator == '*') {
                    System.out.println("add multi result to result " + multiResult);
                    result += multiResult;
                    multiResult = 1;
                }
                currentOperator = operator;
            }

            if (currentOperator == '*') {
                System.out.println("multiplication " + number);
                multiResult *= number;
            } else {
                System.out.println("addition " + number);
                result += number;
            }
        }
        if (currentOperator == '*') {
            System.out.println("add multi result to result " + multiResult);
            result += multiResult;
        }

        return result;
    }

}
