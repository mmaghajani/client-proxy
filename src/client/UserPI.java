package client;

import client.ui.StartPage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by mma on 5/22/2017.
 */
public class UserPI {
    private Socket connection;
    private DataOutputStream outToServer;
    private BufferedReader inFromServer;

    private static UserPI ourInstance = new UserPI();

    public static UserPI getInstance() {
        return ourInstance;
    }

    private UserPI() {
    }

    public void setConnection(Socket connection){
        this.connection = connection;
        try {
            outToServer = new DataOutputStream(
                    connection.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runCommand(String command){
        try {
            outToServer.writeBytes(command);
            String reply = inFromServer.readLine();
            StartPage.getInstance().printToBoard(reply);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
