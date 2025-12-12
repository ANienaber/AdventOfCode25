def solve1(ranges_fresh, available):
    # available_and_fresh = 0
    # i = 0
    # j = 0
    # while(i < len(available) and j < len(ranges_fresh)):
    #     start = int(ranges_fresh[j][0])
    #     end = int(ranges_fresh[j][1])
    #     avail = available[i]
        
    #     if int(avail) > end: 
    #         j += 1
    #         continue
    #     if int(avail) < start: 
    #         i += 1
    #         continue
    #     if int(avail) >= start and int(available[i]) <= end:
    #         available_and_fresh += 1
    #         i += 1
    # return(available_and_fresh)
    
    available_and_fresh = 0
    for a in available:
        for r in ranges_fresh:
            if int(a) >= int(r[0]) and int(a) <= int(r[1]): 
                available_and_fresh += 1
                break
    return available_and_fresh


def solve2(ranges_fresh, available):
    id_num = 0
    l = len(ranges_fresh)
    for i in range(len(ranges_fresh)):
        start = int(ranges_fresh[i][0])
        end = int(ranges_fresh[i][1])
        for j in range(i+1,len(ranges_fresh)):
            other_start = int(ranges_fresh[j][0])
            other_end = int(ranges_fresh[j][1])
            if other_start >= start and other_start <= end:
                other_start = end + 1
            if end >= other_start and end <= other_end:
                #end in other interval
                end = other_start - 1
            #if other_start >= start and other_end <= end:
            #    if start <= other_start -1:
            #        ranges_fresh.insert(i+1,[start, other_start - 1])
            #    if other_end + 1 <= end:
            #        ranges_fresh.insert(i+1,[other_end + 1, end])
            #    break
            if other_start <= start and end >= other_end:
                #ranges_fresh.remove(ranges_fresh[i])
                break
            if start > end:
                break
        
        if start <= end:
            # if interval is still in positive length
            id_num += end - start + 1
            
        print(f"Range done, {i/l*100}%, current number of ids is {id_num}")
                 
    return id_num
    

def main():
    with open("Anna/day5_input.txt", "r") as f:
        out = f.read().split("\n\n")
        ranges_fresh = [line.split("-") for line in out[0].splitlines()]
        available = out[1].splitlines()
    print(solve1(ranges_fresh, available))
    print(solve2(ranges_fresh, available))

if __name__ == "__main__":
    main()