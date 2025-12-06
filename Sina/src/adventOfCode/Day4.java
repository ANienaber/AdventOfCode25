package adventOfCode;

import java.util.Arrays;
import java.util.List;

public class Day4 extends Day{

    public Day4(int day, long testResultPart1, long testResultPart2) {
        super(day, testResultPart1, testResultPart2,
                false,
                2
        );
    }

    protected long part1(List<String> lines) {
        long result = 0;

        for(int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int col = 0; col < line.length(); col++) {
                if (lines.get(row).charAt(col) != '@') {
                    continue;
                }
                int rollsNearby = 0;
                // row above
                if (row != 0) {
                    if (col != 0 && lines.get(row - 1).charAt(col - 1) == '@') { // top left
                        rollsNearby++;
                    }
                    if (lines.get(row - 1).charAt(col) == '@') { // top middle
                        rollsNearby++;
                    }
                    if (col != line.length()-1 && lines.get(row - 1).charAt(col + 1) == '@') { // top right
                        rollsNearby++;
                    }
                }
                // middle row
                if (col != 0 && lines.get(row).charAt(col - 1) == '@') { // middle left
                    rollsNearby++;
                }
                if (col != line.length()-1 && lines.get(row).charAt(col + 1) == '@') { // top right
                    rollsNearby++;
                }
                // row below
                if (row != lines.size() -1) {
                    if (col != 0 && lines.get(row + 1).charAt(col - 1) == '@') { // top left
                        rollsNearby++;
                    }
                    if (lines.get(row + 1).charAt(col) == '@') { // top middle
                        rollsNearby++;
                    }
                    if (col != line.length()-1 && lines.get(row + 1).charAt(col + 1) == '@') { // top right
                        rollsNearby++;
                    }
                }
                if (rollsNearby < 4) {
                    result++;
                }
            }
        }

        return result;
    }

    protected long part2(List<String> lines) {
        long result = 0;

        char[][] diagram = new char[lines.size() + 2][lines.getFirst().length() + 2]; // row, col
        for (int i = 0; i < diagram.length; i++) {
            if (i==0 || i == diagram.length-1) {
                Arrays.fill(diagram[i], '.');
            }
            else {
                diagram[i][0] = '.';
                diagram[i][diagram[i].length-1] = '.';
                System.arraycopy(lines.get(i-1).toCharArray(),0,diagram[i],1,lines.get(i-1).length());
            }
        }

        boolean somethingChanged = true;
        while (somethingChanged) {
            somethingChanged = false;
            print("---------------------------");
            for(int row = 1; row < diagram.length-1; row ++) {
                print(new String(diagram[row]));
                for (int col = 1; col < diagram[0].length-1; col++) {
                    if (diagram[row][col] != '@') {
                        continue;
                    }
                    int rollsNearby = -1;
                    for (int r : new int[]{row - 1, row, row + 1}) {
                        for (int c: new int[]{col - 1, col, col + 1}) {
                            if (diagram[r][c] == '@'){
                                rollsNearby++;
                            }
                        }
                    }
                    if (rollsNearby < 4) {
                        result++;
                        diagram[row][col] = '.';
                        somethingChanged = true;
                    }
                }
            }
            print("" + result);
        }

        return result;
    }
}
