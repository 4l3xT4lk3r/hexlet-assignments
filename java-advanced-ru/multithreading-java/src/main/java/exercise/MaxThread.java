package exercise;

// BEGIN
public class MaxThread extends Thread{

    private final int[] numbers;
    private int max;

    public int getMax() {
        return max;
    }
    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        max = numbers[0];
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }
    }
}
// END
