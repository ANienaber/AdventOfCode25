package adventOfCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DayX {
    private static final int DAY = 1;
    private static final boolean TEST = true;

    public static void main (String[] args) {
        try(BufferedReader br = new BufferedReader(new FileReader("day" + DAY + (TEST ? "_test" : "")))) {
            String line = br.readLine();
            while (line != null) {
                //process line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
