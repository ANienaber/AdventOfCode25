package adventOfCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Day1 {
    private static final int DAY = 1;
    private static final boolean TEST = false;

    public static void main (String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Day1.class.getResourceAsStream("/day" + DAY + (TEST ? "_test" : "") + ".txt"))))) {
            String line = br.readLine();
            int result = 0;
            int currentPosition = 50;

            while (line != null) {
                if (line.startsWith("L")) {
                    currentPosition = (currentPosition + 100 - Integer.parseInt(line.substring(1))) % 100;
                } else if (line.startsWith("R")) {
                    currentPosition = (currentPosition + Integer.parseInt(line.substring(1))) % 100;
                }
                if (currentPosition == 0) {
                    result++;
                }
                line = br.readLine();
            }

            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
