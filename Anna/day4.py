import sys
from typing import Iterable, Tuple

#!/usr/bin/env python3


def solve1(grid):
    accessable_paerrolls = 0
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            adjacent_rolls = 0
            if grid[i][j] == ".":
                continue
            else:
                # not best solution but performance is not that important here :)
                if i > 0:
                    if grid[i-1][j] == "@": adjacent_rolls += 1
                    if j > 0:
                        if grid[i-1][j-1] == "@": adjacent_rolls += 1
                    if j < len(grid[i])-1: 
                        if grid[i-1][j+1] == "@": adjacent_rolls += 1
                        
                if j > 0:
                    if grid[i][j-1] == "@": adjacent_rolls += 1
                if j < len(grid[i])-1: 
                    if grid[i][j+1] == "@": adjacent_rolls += 1
                    
                if i < len(grid)-1:
                    if grid[i+1][j] == "@": adjacent_rolls += 1
                    if j > 0:
                        if grid[i+1][j-1] == "@": adjacent_rolls += 1
                    if j < len(grid[i])-1: 
                        if grid[i+1][j+1] == "@": adjacent_rolls += 1
                
                if adjacent_rolls < 4: 
                    accessable_paerrolls += 1
                    print(f"({i}, {j})")
                
                
    return accessable_paerrolls


def solve2(grid):
    accessable_paerrolls = 0
    rolls
    for i in range(len(grid)):
        for j in range(len(grid[i])):
            adjacent_rolls = 0
            if grid[i][j] == ".":
                continue
            else:
                # not best solution but performance is not that important here :)
                if i > 0:
                    if grid[i-1][j] == "@": adjacent_rolls += 1
                    if j > 0:
                        if grid[i-1][j-1] == "@": adjacent_rolls += 1
                    if j < len(grid[i])-1: 
                        if grid[i-1][j+1] == "@": adjacent_rolls += 1
                        
                if j > 0:
                    if grid[i][j-1] == "@": adjacent_rolls += 1
                if j < len(grid[i])-1: 
                    if grid[i][j+1] == "@": adjacent_rolls += 1
                    
                if i < len(grid)-1:
                    if grid[i+1][j] == "@": adjacent_rolls += 1
                    if j > 0:
                        if grid[i+1][j-1] == "@": adjacent_rolls += 1
                    if j < len(grid[i])-1: 
                        if grid[i+1][j+1] == "@": adjacent_rolls += 1
                
                if adjacent_rolls < 4: 
                    accessable_paerrolls += 1
                    print(f"({i}, {j})")
                
                
    return accessable_paerrolls
    return 

def main():
    with open("Anna/day4_input.txt", "r") as file:
        grid = file.read().splitlines()
    print(solve1(grid))
    print(solve2(grid))


if __name__ == "__main__":
    main()