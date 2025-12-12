import numpy as np
from skimage.measure import label, regionprops
from matplotlib.path import Path

def solve1(coords):
    max_rectangle = 0
    max_coords = [[0,0], [0,0]]
    for c1 in coords:
        for c2 in coords:
            x_length = c1[0] - c2[0] + 1
            y_length = c1[1] - c2[1] + 1
            rect1 = x_length * y_length
            
            x_length = c2[0] - c1[0] + 1 
            y_length = c2[1] - c1[1] + 1
            rect2 = x_length * y_length

            if max(rect1, rect2) > max_rectangle:
                max_coords = [c1, c2]
                max_rectangle = max(max_rectangle, max(rect1, rect2))
    return max_rectangle, max_coords

def solve2(coords):
    max_rectangle = 0
    polygon = Path(coords)
    
    max_coords = [[0,0], [0,0]]
        
    for c1 in coords:
        for c2 in coords:
            xmin = min(c1[0], c2[0])
            xmax = max(c1[0], c2[0])
            ymin = min(c1[1], c2[1])
            ymax = max(c1[1], c2[1])
            
            x_coords = np.arange(xmin, xmax+1)
            y_coords = np.arange(ymin, ymax+1)
            xx, yy = np.meshgrid(x_coords, y_coords)
            rectangle_points = np.vstack([xx.ravel(), yy.ravel()]).T

            mask = polygon.contains_points(rectangle_points)
            if mask.all():
                width = abs(xmin - xmax)
                height = abs(ymin - ymax)
                if width * height > max_rectangle:
                    max_coords = [c1, c2]
                    max_rectangle = max(max_rectangle, width * height)
        
    return max_rectangle, max_coords

        
                


def main():
    with open("Anna/day9_input.txt", "r") as f:
        coords = [o.split(",") for o in f.read().splitlines()]
    coords = [[int(c[0]), int(c[1])] for c in coords]
    print(solve1(coords))
    print(solve2(coords))


if __name__ == "__main__":
    main()