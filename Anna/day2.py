import sys
from typing import Iterable, Tuple

#!/usr/bin/env python3


def solve1(ranges):
    count = 0
    for r in ranges:
        range_parts = r.split("-")
        start = int(range_parts[0])
        end = int(range_parts[1])

        for i in range(start, end + 1):
            first_halve = str(i)[:len(str(i))//2]
            second_halve = str(i)[len(str(i))//2:]
            if first_halve == second_halve:
                count += i
    
    return count

def solve2(ranges):
    count = 0
    for r in ranges:
        range_parts = r.split("-")
        start = int(range_parts[0])
        end = int(range_parts[1])

        for i in range(start, end + 1):
            for j in range(1, len(str(i))):
                if len(str(i)) % j == 0:
                    nums = [str(i)[k:k+j] for k in range (0, len(str(i)), j)]
                    if len(set(nums)) == 1:
                        count += i
                        break
    return count

def main():
    with open("Anna/day2_input.txt", "r") as file:
        ranges = file.read().split(",")
    print(solve1(ranges))
    print(solve2(ranges))


if __name__ == "__main__":
    main()