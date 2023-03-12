package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
public class App {
    public static long getCountOfFreeEmails(List<String> emailList) {
        List<String> freeEmails = List.of("gmail.com", "yandex.ru", "hotmail.com");
        return emailList.stream().filter(email -> freeEmails.contains(email.split("@")[1])).count();
    }
}
// END
