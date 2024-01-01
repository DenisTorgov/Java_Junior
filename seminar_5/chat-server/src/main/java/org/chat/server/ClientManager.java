package org.chat.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable {

    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;
    public final static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager (Socket socket) {
        this.socket = socket;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clients.add(this);
            System.out.println(name + " connected to the chat.");
            broadcastMessage("Server: " + name + " connected to the chat.");
        }
        catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                /*if (massageFromClient == null){
                    // для  macOS
                    closeEverything(socket, bufferedReader, bufferedWriter);
                    break;
                }*/
                int index = messageFromClient.indexOf(" ") + 1;
                if (messageFromClient.charAt(index) == '@') {
                    String privateRecipient = messageFromClient
                            .substring(index, messageFromClient.length());
                    privateRecipient = privateRecipient.substring(1, privateRecipient.indexOf(" "));
                    privateMessage(privateRecipient, messageFromClient);
                } else {
                    broadcastMessage(messageFromClient);
                }
            }
            catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }
    private void broadcastMessage(String message) {
        for (ClientManager client: clients) {
            try {
                if(!client.name.equals(name)) {
                    client.bufferedWriter.write(message);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            }
            catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }
    private void privateMessage (String privateRecipient, String privateMessage) {
        for (ClientManager client: clients) {
            try {
                if(client.name.equals(privateRecipient)) {
                    client.bufferedWriter.write("Private message from->" + privateMessage);
                    client.bufferedWriter.newLine();
                    client.bufferedWriter.flush();
                }
            }
            catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }
    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        //Remove client from collection
        removeClient();
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void removeClient() {
        clients.remove(this);
        System.out.println(name + " left the chat.");
        broadcastMessage("Server: " + name + " left the chat.");
    }
}
