package exercise;

import java.util.Arrays;

// BEGIN
public class ReversedSequence implements CharSequence {

    private final char[] sequence;

    public ReversedSequence(String string) {
        sequence = new StringBuilder(string).reverse().toString().toCharArray();
    }

    @Override
    public int length() {
        return sequence.length;
    }

    @Override
    public char charAt(int index) {
        return sequence[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        char[] subSequence = new char[end - start];
        System.arraycopy(sequence, start, subSequence, 0, subSequence.length);
        return new ReversedSequence(new StringBuilder(new String(subSequence)).reverse().toString());
    }

    @Override
    public String toString() {
        return new String(sequence);
    }
}
// END
