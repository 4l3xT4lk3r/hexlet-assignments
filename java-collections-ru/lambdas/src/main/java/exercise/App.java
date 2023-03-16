package exercise;

import java.util.Arrays;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        if (image == null) {
            return new String[0][];
        }
        int scale = 2;
        String[][] result = new String[image.length * scale][];
        int i = 0;
        for (String[] line : image) {
            if (line.length == 0) {
                return new String[0][];
            }
            for (int j = 0; j < scale; j++) {
                result[i++] = Arrays.stream(line)
                        .map(x -> x.repeat(scale))
                        .collect(Collectors.joining())
                        .split("");
            }
        }
        return result;
    }
}
// END
