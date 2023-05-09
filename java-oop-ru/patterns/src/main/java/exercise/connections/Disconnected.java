package exercise.connections;

// BEGIN
public class Disconnected implements Connection {
    @Override
    public String getCurrentState() {
        return "disconnected";
    }

    @Override
    public void connect() {
        System.out.println("Error");
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnected!");
    }

    @Override
    public void write(String data) {
        System.out.println("Cant write data " + data + " while disconnected!");
    }
}

// END
