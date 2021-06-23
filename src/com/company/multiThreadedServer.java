package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;


public class multiThreadedServer
{
    static Set<String> userNames = new HashSet<>();
    static Set<Messaging> userThreads = new HashSet<>();

    public static void main(String[] args) throws IOException {
//        ChatServer chatServer = new ChatServer()
    multiThreadedServer multiThreadedServer = new multiThreadedServer();
        multiThreadedServer.startabc();
    }

    public void startabc() throws IOException {
        ServerSocket socket = new ServerSocket(9000);
        System.out.println("Server Started ");

        while (true)
        {
            Socket clientSocket = socket.accept();
            System.out.println("Client connected");
            Messaging client = new Messaging(clientSocket,this);
            userThreads.add(client);
            client.start();
        }
    }

    void addUser(String userName)
    {
        userNames.add(userName);
    }
    void removeUser(String userName, Messaging thread)
    {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(thread);
            System.out.println("The user " + userName + " quitted");
        }
    }
    void broadcast(String message, Messaging excludeUser) throws FileNotFoundException {
//        System.out.println(userThreads.size());
        for (Messaging aUser : userThreads) {
            if (aUser != excludeUser) {
                try {
                    aUser.sendMessage(message);
//                    System.out.println("message: "+message);
//                    PrintWriter out = new PrintWriter(message);
                }
                 catch (Exception e) {
                    e.printStackTrace();
                }
//                aUser.sendMessage(message);
            }
        }
    }
}

//    Ayush send:- Ayush :- hi
//        src/com/company/client2.java