package adventOfCode;

import java.util.Arrays;
import java.util.List;

public class Day7 extends Day{

    public Day7(int day, long testResultPart1, long testResultPart2) {
        super(day, testResultPart1, testResultPart2,
                false,
                2
        );
    }

    protected long part1(List<String> lines) {
        long result = 0;

        char[][] diagram = new char[lines.size()][lines.getFirst().length()]; // row, col
        for (int i = 0; i < diagram.length; i++) {
            diagram[i] = lines.get(i).toCharArray();
        }

        for (int row = 0; row < diagram.length - 1; row++) {
            char beam = row == 0 ? 'S' : '|';
            for (int col = 0; col < diagram[0].length; col++) {
                if (diagram[row][col] != beam) {
                    continue;
                }
                //propagate
                if (diagram[row+1][col] == '^') {
                    result += 1;
                    if (col != 0) {
                        diagram[row+1][col-1] = '|';
                    }
                    if (col != (diagram[0].length - 1)) {
                        diagram[row+1][col+1] = '|';
                    }
                } else {
                    diagram[row+1][col] = '|';
                }
            }
            print(new String(diagram[row]));
        }
        print(new String(diagram[diagram.length-1]));
        return result;
    }

    protected long part2(List<String> lines) {
        long result = 0;

        char[][] diagram = new char[lines.size()][lines.getFirst().length()]; // row, col
        for (int i = 0; i < diagram.length; i++) {
            diagram[i] = lines.get(i).toCharArray();
        }

        long[][] waysToGetHere = new long[diagram.length][diagram[0].length];
        waysToGetHere[0][lines.getFirst().indexOf('S')] = 1;
        for (int row = 0; row < diagram.length - 1; row++) {
            char beam = row == 0 ? 'S' : '|';
            for (int col = 0; col < diagram[0].length; col++) {
                if (diagram[row][col] != beam) {
                    continue;
                }
                //propagate
                if (diagram[row+1][col] == '^') {
                    if (col != 0) {
                        diagram[row+1][col-1] = '|';
                        waysToGetHere[row+1][col-1] = waysToGetHere[row+1][col-1] + waysToGetHere[row][col];
                    }
                    if (col != (diagram[0].length - 1)) {
                        diagram[row+1][col+1] = '|';
                        waysToGetHere[row+1][col+1] = waysToGetHere[row+1][col+1] + waysToGetHere[row][col];
                    }
                } else {
                    diagram[row+1][col] = '|';
                    waysToGetHere[row+1][col] = waysToGetHere[row+1][col] + waysToGetHere[row][col];
                }
            }
            print(new String(diagram[row]));
        }
        print(new String(diagram[diagram.length-1]));
        for (long[] row : waysToGetHere) {
            print(Arrays.toString(row));
        }

        for (long val : waysToGetHere[waysToGetHere.length-1]) {
            result += val;
        }
        return result;
    }

}
