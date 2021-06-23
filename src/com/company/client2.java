package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class client2 {

    public static void main(String[] args) throws IOException {
        // write your code here
        String ip = "localhost";
        int port = 9000;
        Socket s = new Socket(ip,port);
        String str = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out = new PrintWriter(os);

        Scanner scanner = new Scanner(System.in);

        boolean entry = false;

        String name = "";
        while (true)
        {

            if (entry == false)
            {
                System.out.println("Enter Your Name :- ");
                name = scanner.nextLine();
                out.println(name);
                out.flush();
                entry = true;
            }
            System.out.println("Enter Your message :- ");
            str = scanner.nextLine();
            out.println(name + " :- " + str);
            out.flush();
            String res = in.readLine();
            System.out.println("response :- " + res);
            if (str.equals("exit")) {
                entry = false;
                break;
            }
        }
//        BufferedReader =
    }
}
