package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args) throws Exception {
        System.out.println("Waiting for client request");
        ServerSocket ss = new ServerSocket(9000);
        Socket s = ss.accept();
//        System.out.println("request address " + s);
//        System.out.println("");
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream());
        String name = in.readLine();
        System.out.println(name + " connected");
        while (true)
        {
            String str = in.readLine();
            System.out.println("client data " + str);
            out.println("server acknowledged " + str);
            out.flush();
            if (str.equals("exit")) break;
        }
    }
}
