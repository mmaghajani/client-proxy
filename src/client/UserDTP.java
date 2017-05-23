package client;

import client.ui.StartPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mma on 5/22/2017.
 */
public class UserDTP implements Runnable {
    ServerSocket welcomeSocket;
    @Override
    public void run() {
        try {
            welcomeSocket = new ServerSocket(6000);
            Socket dataConnection = welcomeSocket.accept();
            BufferedReader inFromServerDTP = new BufferedReader(
                    new InputStreamReader(dataConnection.getInputStream()));
            boolean loop = true;
            StringBuilder sb = new StringBuilder();
            while (loop) {
                if (inFromServerDTP.ready()) {
                    int i = 0;
                    while (i != -1) {
                        i = inFromServerDTP.read();
                        sb.append((char) i);
                    }
                    loop = false;
                }
            }
            StartPage.getInstance().printToBoard(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            welcomeSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
