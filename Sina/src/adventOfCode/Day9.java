package adventOfCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class Day9 extends Day{

    public Day9(int day) {
        super(day,
                50,
                24,
                false,
                2
        );
    }

    protected long part1(List<String> lines) {
        long result = 0;

        List<Tile> tiles = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(",");
            tiles.add(new Tile(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }

        for (Tile tile1: tiles) {
            for (Tile tile2: tiles) {
                long rectSize = (long) Math.abs(tile1.x - tile2.x + 1) * Math.abs(tile1.y - tile2.y + 1);
                if (rectSize > result) {
                    result = rectSize;
                }
            }
        }

        return result;
    }

    protected long part2(List<String> lines) {
        long result = 0;

        List<Tile> tiles = new ArrayList<>();
        int biggestX = 0;
        int biggestY = 0;
        for (String line : lines) {
            String[] split = line.split(",");
            Tile newTile = new Tile(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            if (newTile.x > biggestX) {
                biggestX = newTile.x;
            }
            if (newTile.y > biggestY) {
                biggestY = newTile.y;
            }
            tiles.add(newTile);
        }

        BitSet[] green = new BitSet[biggestX+1];
        for (int i=0; i<green.length; i++) {
            green[i] = new BitSet(biggestY+1);
        }
        System.out.println("writing green lines");
        for (int i=0;i<tiles.size();i++) {
            int nextI = i == tiles.size()-1 ? 0 : i+1;
            Tile tile1 = tiles.get(i);
            Tile tile2 = tiles.get(nextI);
            if (tile1.x == tile2.x) {
                int x = tile1.x;
                for (int y = Math.min(tile1.y, tile2.y); y <= Math.max(tile1.y, tile2.y); y++) {
                    green[x].set(y);
                    print("red tile at " + x + " " + y);
                }
            }
            if (tile1.y == tile2.y) {
                int y = tile1.y;
                for (int x = Math.min(tile1.x, tile2.x); x <= Math.max(tile1.x, tile2.x); x++) {
                    green[x].set(y);
                    print("red tile at " + x + " " + y);
                }
            }
        }

        print("");

        System.out.println("filling green");
        boolean fill = false;
        for (int j=0; j<green.length;j++) {
            System.out.println("filling BitSet " + j);
            BitSet greenRow = green[j];
            for (int i=0; i<greenRow.size();i++) {
                if (!greenRow.get(i) && fill) {
                    greenRow.set(i);
                    print("fill in at " + i);
                } else if (greenRow.get(i)) {
                    fill = !fill;
                }
            }
        }

        System.out.println("searching rects");
        for (int i = 0; i<tiles.size(); i++) {
            System.out.println("tile1: " + i);
            Tile tile1 = tiles.get(i);
            for (Tile tile2: tiles) {
                long rectSize = (long) (Math.abs(tile1.x - tile2.x)+1) * (Math.abs(tile1.y - tile2.y)+1);
                if (rectSize > result) {

                    boolean valid = true;
                    for (int x = Math.min(tile1.x, tile2.x); x <= Math.max(tile1.x, tile2.x); x++) {
                        for (int y = Math.min(tile1.y, tile2.y); y <= Math.max(tile1.y, tile2.y); y++) {
                            if (!green[x].get(y)) {
                                valid = false;
                                break;
                            }
                        }
                        if (!valid) {
                            break;
                        }
                    }
                    if (valid) {
                        System.out.println("result changed: " + rectSize);
                        result = rectSize;
                    }
                }
            }
        }

        return result;
    }

    protected long old_part2(List<String> lines) {
        long result = 0;

        List<Tile> tiles = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(",");
            tiles.add(new Tile(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }

        List<HorizontalLine> horizontalLines = new ArrayList<>();
        List<VerticalLine> verticalLines = new ArrayList<>();
        for (int i=0;i<tiles.size();i++) {
            int nextI = i == tiles.size()-1 ? 0 : i+1;
            Tile tile1 = tiles.get(i);
            Tile tile2 = tiles.get(nextI);
            if (tile1.y == tile2.y) {
                horizontalLines.add(new HorizontalLine(Math.min(tile1.x, tile2.x), Math.max(tile1.x, tile2.x), tile1.y));
            } else if (tile1.x == tile2.x) {
                verticalLines.add(new VerticalLine(tile1.x, Math.min(tile1.y, tile2.y), Math.max(tile1.y, tile2.y)));
            }
        }
        print(horizontalLines.toString());

        for (Tile tile1: tiles) {
            for (Tile tile2: tiles) {
                long rectSize = (long) (Math.abs(tile1.x - tile2.x) + 1) * (Math.abs(tile1.y - tile2.y) + 1);
                if (rectSize > result) {
                    boolean valid = true;
                    print("testing rect " + tile1 + " " + tile2 + " size: " + rectSize);
                    for (int x = Math.min(tile1.x, tile2.x); x <= Math.max(tile1.x, tile2.x); x++) {
                        for (int y = Math.min(tile1.y, tile2.y); y <= Math.max(tile1.y, tile2.y); y++) {
                            int finalY = y;
                            int finalX = x;
                            if (horizontalLines.stream().anyMatch(l -> l.y == finalY && l.x1 <= finalX && l.x2 >= finalX)) {
                                print("tile "+ finalX + " " + finalY + " green on horizontal line");
                                continue;
                            }
                            if (verticalLines.stream().anyMatch(l -> l.x == finalX && l.y1 <= finalY && l.y2 >= finalY)) {
                                print("tile "+ finalX + " " + finalY + " green on vertical line");
                                continue;
                            }
                            boolean filled = false;
                            for (HorizontalLine l: horizontalLines) {
                                if (l.x1 < finalX && l.x2 > finalX && l.y < finalY) {
                                    filled = !filled;
                                } else if (l.x1 <= finalX && l.x2 >= finalX && l.y > finalY) {
                                    if (filled) {
                                        print("tile "+ finalX + " " + finalY + " green in rect");
                                    }
                                    valid = filled;
                                    break;
                                }
                            }
                            if (!valid) {
                                for (HorizontalLine l: horizontalLines.reversed()) {
                                    if (l.x1 < finalX && l.x2 > finalX && l.y > finalY) {
                                        filled = !filled;
                                    } else if (l.x1 <= finalX && l.x2 >= finalX && l.y < finalY) {
                                        if (filled) {
                                            print("tile "+ finalX + " " + finalY + " green in rect");
                                        } else {
                                            print("tile "+ finalX + " " + finalY + " NOT green in rect");
                                        }
                                        valid = filled;
                                        break;
                                    }
                                }
                                if (!valid) {
                                    break;
                                }
                            }
                        }
                        if (!valid) {
                            break;
                        }
                    }
                    if (valid) {
                        result = rectSize;
                    }
                }
            }
        }

        return result;
    }

    private record Tile(int x, int y){}
    private record HorizontalLine(int x1, int x2, int y){}
    private record VerticalLine(int x, int y1, int y2){}
}
