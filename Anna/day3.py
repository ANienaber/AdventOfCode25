import sys
from typing import Iterable, Tuple

#!/usr/bin/env python3


def solve1(banks):
    joltage = 0

    for banks in banks:
        first_battery = "0"
        second_battery = "0"
        index_first = 0
        for i in range(len(banks) - 1):
            if banks[i] > first_battery:
                first_battery = banks[i]
                index_first = i
        for j in range(index_first + 1,len(banks)):
            if banks[j] > second_battery:
                second_battery = banks[j]
        joltage += int(f"{first_battery}{second_battery}")
    return joltage


def solve2(banks):
    joltage = 0

    for banks in banks:
        powered_batteries = ""
        index = -1
        for x in range(1, 13):
            battery = "0"
            for i in range(index + 1, len(banks) - (12 - x)):
                if banks[i] > battery:
                    battery = banks[i]
                    index = i
            
            powered_batteries = f"{powered_batteries}{battery}"
        joltage += int(powered_batteries)
    return joltage

def main():
    with open("Anna/day3_input.txt", "r") as file:
        banks = file.read().splitlines()
    print(solve1(banks))
    print(solve2(banks))


if __name__ == "__main__":
    main()