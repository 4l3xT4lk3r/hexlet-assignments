package exercise;
import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {
    Connection connection;

    private String ipAddress;
    private int tcpPort;

    public TcpConnection(String ipAddress, int tcpPort){
        this.ipAddress = ipAddress;
        this.tcpPort = tcpPort;
    }

    public String getCurrentState(){
        return connection.getCurrentState();
    };
    void connect(){
        if ( connection == null || connection instanceof Disconnected) {
            connection = new Connected();
            connection.connect();
        }else{
            System.out.println("Error");
        }
    }
    void disconnect(){
        if ( connection instanceof Connected) {
            connection = new Disconnected();
            connection.disconnect();
        }else {
            System.out.println("Error");
        }
    }
   void write(String data){
        if ( connection instanceof Connected){
            connection.write(data);
        }else {
            System.out.println("Error");
        }
   }



}

// END
