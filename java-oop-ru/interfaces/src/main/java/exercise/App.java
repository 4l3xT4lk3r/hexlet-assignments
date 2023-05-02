package exercise;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homeList, int n) {
        List<String> result = homeList.stream()
                .sorted(Home::compareTo)
                .map(Object::toString)
                .limit(n)
                .toList();
        return result;
    }
}

// END
