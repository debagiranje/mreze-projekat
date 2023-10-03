package klijent.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Konekcija {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private static volatile Konekcija instance;

    private Konekcija() {
    }

    public static Konekcija getInstance() {
        if (instance == null) {
            synchronized (Konekcija.class) {
                if (instance == null) {
                    instance = new Konekcija();
                }
            }
        }
        return instance;
    }

    public synchronized void connectToServer(String serverAddress, int serverPort) throws IOException {
        socket = new Socket(serverAddress, serverPort);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public synchronized void sendMessage(String message) {
        out.println(message);
    }

    public synchronized String receiveMessage() throws IOException {
        return in.readLine();
    }

    public synchronized void closeConnection() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }
}
