package exercise.connections;

// BEGIN
public class Connected implements Connection {

    @Override
    public String getCurrentState() {
        return "connected";
    }

    @Override
    public void connect() {
        System.out.println("Connected!");
    }

    @Override
    public void disconnect() {
        System.out.println("Error!");
    }

    @Override
    public void write(String data) {
        System.out.println("Write data " + data + " to buffer!");
    }
}


// END
