package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
public class App {

    public static String getForwardedVariables(String fileContent){
        String result = fileContent.lines()
                .filter(string -> string.startsWith("environment"))
                .map(string -> string.replaceAll("environment=|\"", ""))
                .map(string -> string.split(","))
                .flatMap(Arrays::stream)
                .filter(string -> string.startsWith("X_FORWARDED_"))
                .map(string -> string.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));
        return result;
    }
}
//END
