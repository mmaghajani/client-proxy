package client;

import client.ui.StartPage;

import java.io.*;
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

    public String getText(){
        Socket dataConnection = null;
        try {
            dataConnection = welcomeSocket.accept();
            BufferedReader inFromServerDTP = new BufferedReader(
                    new InputStreamReader(dataConnection.getInputStream()));
            boolean loop = true;
            StringBuilder sb = new StringBuilder();
            System.out.println("salamal");
            while (loop) {
                if (inFromServerDTP.ready()) {
                    int i = 0;
                    while (i != -1) {
                        i = inFromServerDTP.read();
                        System.out.print(i);
                        sb.append((char) i);
                    }
                    loop = false;
                }
            }
            dataConnection.close();
            welcomeSocket.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getFile(String name){
        Socket dataConnection = null;
        try {
            dataConnection = welcomeSocket.accept();
            File file = new File("./files/" + name);
            file.createNewFile();

            byte[] bytes = new byte[1024];
            int length;
            final FileOutputStream fileOutputStream = new FileOutputStream(file);
            final InputStream inputStream = dataConnection.getInputStream();
            while ((length = inputStream.read(bytes)) != -1) {
                // If the end of the header had already been reached, write the bytes to the file as normal.

                    fileOutputStream.write(bytes, 0, length);

                    // This locates the end of the header by comparing the current byte as well as the next 3 bytes
                    // with the HTTP header end "\r\n\r\n" (which in integer representation would be 13 10 13 10).
                    // If the end of the header is reached, the flag is set to true and the remaining data in the
                    // currently buffered byte array is written into the file.

            }
            inputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
