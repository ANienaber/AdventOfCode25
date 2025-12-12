import re
import math
def solve1(shapes, areas):
    regions_possible = 0
    
    for a in areas:
        m = re.match(r"(\d+)x(\d+):", a[0])
        if m:
            x = int(m.group(1))
            y = int(m.group(2))
        overall_shapes = 0
        for i in range(1, len(a)):
            overall_shapes += int(a[i])
        possible_shapes = math.floor(x/3) * math.floor(y/3)
        if possible_shapes >= overall_shapes:
            regions_possible += 1
            
    return regions_possible

def solve2(input_data):
    pass

def main():
    with open('Anna/day12_input.txt', 'r') as f:
        input_data = f.read().split("\n\n")
        lines = []
        for i in range(len(input_data)):
            lines.append(input_data[i].splitlines())
        shapes = []
        i = 0
        while(lines[i][0][1] == ":" and i < len(lines)):
            del lines[i][0]
            shapes.append(lines[i])
            i+=1
            
        areas = [l.split(" ") for l in input_data.pop().splitlines()]
        
        
    result1 = solve1(shapes, areas)
    print(f"Part 1: {result1}")
    
    result2 = solve2(input_data)
    print(f"Part 2: {result2}")

if __name__ == "__main__":
    main()