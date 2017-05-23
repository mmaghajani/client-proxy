package client;

import client.ui.StartPage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
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

    public void runCommand(String clientSentence){
        try {

            String command = clientSentence.split("\\s")[0];
            String reply = "";
            System.out.println(command);
            switch (command){
                case "USER" :
                    outToServer.writeBytes(clientSentence);
                    reply = inFromServer.readLine();
                    StartPage.getInstance().printToBoard(reply);
                    break;
                case "PASS" :
                    outToServer.writeBytes(clientSentence);
                    reply = inFromServer.readLine();
                    StartPage.getInstance().printToBoard(reply);
                    break;
                case "LIST" :
                    outToServer.writeBytes("PORT 6000\n");
                    reply = inFromServer.readLine();
                    String status = reply.split("\\s")[0];
                    System.out.println(status);
                    if( status.equals("200")){
                        UserDTP DTP = new UserDTP();
                        new Thread(DTP).start();
                        outToServer.writeBytes(clientSentence);
                        reply = inFromServer.readLine();
                        System.out.println(reply);
                        StartPage.getInstance().printToBoard(reply);
                        String LISTStatus = reply.split("\\s")[0];
                        if( LISTStatus.equals("200")){
                            DTP.accept();
                        }else
                            DTP.close();
                    }else{
                        StartPage.getInstance().printToBoard(reply);
                    }
                    break;
                case "RETR" :
                    break;
                case "RMD" :
                    break;
                case "DELE" :
                    break;
                case "QUIT" :
                    outToServer.writeBytes(clientSentence);
                    connection.close();
                    break;
                default:
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
