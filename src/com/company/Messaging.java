package com.company;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Messaging extends Thread{
    private Socket client;
    private multiThreadedServer Server;
    public Messaging(Socket client,multiThreadedServer Server) {
        this.client = client;
        this.Server = Server;
    }

    private PrintWriter writer;

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream());

            OutputStream output = client.getOutputStream();
            writer = new PrintWriter(output, true);

            String name = in.readLine();
            Server.addUser(name); //--> null.addUser(name)
            out.println("server acknowledged " + name);
            Server.broadcast("User " + name + " connected\n",this);

            while (true)
            {
                String str = in.readLine();
                System.out.println(str);
                Server.broadcast(str + "\n",this);
//                out.println("server acknowledged " + str);
//                out.flush();
                if (str.equals("exit")) {
                    Server.broadcast(name + "Left\n", this);
                    Server.removeUser(name,this);

                }
                else
                {
                    System.out.println("in brodcast");
                    Server.broadcast(str + "\n",this);
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void sendMessage(String message) {
        writer.println(message);
    }
}
