package exercise;

// BEGIN
public interface Home {
    default int compareTo(Home home) {
        return Double.compare(this.getArea(), home.getArea());
    }

    double getArea();
}
// END
