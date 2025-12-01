def main():
    with open("Anna/day1_input.txt", "r") as file:
        input = file.read().splitlines()
    
    print(solve1(input))    
    print(solve2(input))
    
    
    
def solve1(input: list[str]):
    pos = 50
    counter = 0
    for line in input:
        dir = line[0]
        distance = line[1:]
        if dir == "L":
            distance = int(distance)
            pos = pos - distance
            pos = pos % 100
        if dir == "R":
            distance = int(distance)
            pos = pos + distance
            pos = pos % 100
        #print(pos)
        if pos == 0:
            counter+= 1
    return counter 

def solve2(input: list[str]):
    pos = 50
    counter = 0
    for line in input:
        dir = line[0]
        distance = line[1:]
        distance = int(distance)
        
        if dir == "L":
            while distance > 100:
                distance -= 100
                counter += 1
            pos_old = pos
            pos = pos - distance 
            if pos <= 0 and pos_old != 0:
                counter += 1
            pos = pos % 100
            
        if dir == "R":
            while distance > 100:
                distance -= 100
                counter += 1
            pos = pos + distance 
            if pos >= 100:
                counter += 1
            pos = pos % 100
    return counter 

if __name__ == "__main__":
    main()