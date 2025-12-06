package adventOfCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day5 extends Day{

    public Day5(int day, long testResultPart1, long testResultPart2) {
        super(day, testResultPart1, testResultPart2,
                false,
                2
        );
    }

    protected long part1(List<String> lines) {
        long result = 0;

        List<Range> ranges = new ArrayList<>();
        boolean rangeMode = true;
        for(String line: lines) {
            if (line.isEmpty()) {
                rangeMode = false;
                print(ranges.toString());
                continue;
            }

            if (rangeMode){
                ranges.add(new Range(
                        Long.parseLong(line.substring(0,line.indexOf('-'))),
                        Long.parseLong(line.substring(line.indexOf('-')+1))));
            } else {
                long id = Long.parseLong(line);
                for (Range range : ranges) {
                    if (range.begin <= id && range.end >= id) {
                        result++;
                        break;
                    }
                }
            }
        }

        return result;
    }

    protected long part2(List<String> lines) {
        long result = 0;

        List<Range> ranges = new ArrayList<>();
        for(String line: lines) {
            if (line.isEmpty()) {
                break;
            }

            ranges.add(new Range(
                    Long.parseLong(line.substring(0,line.indexOf('-'))),
                    Long.parseLong(line.substring(line.indexOf('-')+1))));
        }
        print(ranges.toString());
        ranges.sort(Comparator.comparing(range -> range.begin));
        for (int i=0; i<ranges.size(); i++) {
            long begin = ranges.get(i).begin;
            long end = ranges.get(i).end;
            //System.out.println("begin: " + begin + " end: " + end + " adding to result: " + (end-begin+1));
            result+=end-begin+1;
            int next = i+1;
            List<Range> rangesToRemove = new ArrayList<>();
            while (next < ranges.size() && ranges.get(next).begin <= end) {
                long nextEnd = ranges.get(next).end;
                if (nextEnd <= end) {
                    rangesToRemove.add(ranges.get(next));
                } else {
                    ranges.set(next, new Range(end+1, nextEnd));
                }
                next++;
            }
            for (Range range : rangesToRemove) {
                ranges.remove(range);
            }
        }

        return result;
    }

    private record Range(long begin, long end) {}
}
