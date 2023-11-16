package exercise;

// BEGIN
public class MinThread extends Thread {
    private final int[] numbers;

    private int min;

    public int getMin() {
        return min;
    }

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        min = numbers[0];
        for (int number : numbers) {
            if (number < min) {
                System.out.println(number);
                min = number;
            }
        }
    }
}
// END
