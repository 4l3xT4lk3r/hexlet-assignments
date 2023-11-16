package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {

        MaxThread t1 = new MaxThread(numbers);
        MinThread t2 = new MinThread(numbers);

        t1.start();
        t2.start();
        LOGGER.info(t1.getName() + " START");
        LOGGER.info(t2.getName() + " START");
        try {
            t1.join();
            t2.join();
        }catch (Exception e){
           e.printStackTrace();
        }
        LOGGER.info(t1.getName() + " FINISH");
        LOGGER.info(t2.getName() + " FINISH");
        return Map.of("max", t1.getMax(), "min", t2.getMin());
    }
    // END
}
