package adventOfCode;

import java.util.Arrays;
import java.util.List;

public class Day2 extends Day{

    public Day2(int day, long testResultPart1, long testResultPart2) {
        super(day, testResultPart1, testResultPart2,
                false,
                2
        );
    }

    protected long part1(List<String> lines) {
        long result = 0;

        for(String line: lines) {
            String[] ranges = line.split(",");
            print(Arrays.toString(ranges));
            for (String range: ranges) {
                long start = Long.parseLong(range.split("-")[0]);
                long end = Long.parseLong(range.split("-")[1]);
                for (long i = start; i <= end; i++) {

                    String iString = String.valueOf(i);
                    int length = iString.length();
                    if (length % 2 != 0) {
                        i = (long) Math.pow(10, iString.length()) - 1; //set to next even number
                        continue;
                    }

                    boolean valid = false;
                    for (int j = 0; j < length/2; j++) {
                        if (iString.charAt(j) != iString.charAt(length/2 + j)) {
                            valid = true;
                            break;
                        }
                    }
                    if (!valid) {
                        result += i;
                        print("invalidNo " + i);
                    }
                }
            }
        }

        return result;
    }

    protected long part2(List<String> lines) {
        long result = 0;

        for(String line: lines) {
            String[] ranges = line.split(",");
            print(Arrays.toString(ranges));
            for (String range: ranges) {
                long start = Long.parseLong(range.split("-")[0]);
                long end = Long.parseLong(range.split("-")[1]);
                for (long i = start; i <= end; i++) {
                    String iString = String.valueOf(i);
                    int length = iString.length();
                    for (int repNoLen = 1; repNoLen <= length/2; repNoLen++) {
                        if (length % repNoLen != 0) continue;
                        boolean valid = false;
                        String repNo = iString.substring(0,repNoLen);
                        for (int j = 0; j < length; j++) {
                            if (repNo.charAt(j % repNoLen) != iString.charAt(j)){
                                valid = true;
                                break;
                            }
                        }
                        if (!valid) {
                            result += i;
                            System.out.println("invalidNo " + i);
                            System.out.println("repNo" + repNo);
                            break;
                        }
                    }
                }
            }
        }

        return result;
    }
}
