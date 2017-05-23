package client;


import client.ui.*;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by mma on 5/19/17.
 */

public class Main {

    public static void main( String[] args){


        StartPage.getInstance().printToBoard("Creating Client Socket!\n");
        try {
            Socket clientSocket = new Socket("192.168.1.5", 8000);
            StartPage.getInstance().printToBoard("Connected To Server\n");
            UserPI.getInstance().setConnection(clientSocket);
        } catch (IOException e) {
            StartPage.getInstance().printToBoard("Connection Error.Please Relaunch the App");
        }
    }
}

