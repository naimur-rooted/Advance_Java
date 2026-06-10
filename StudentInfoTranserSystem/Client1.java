package com.mycompany.client1;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client1 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            // using scanner
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Marks (3 subjects separated by space): ");
            int m1 = sc.nextInt();
            int m2 = sc.nextInt();
            int m3 = sc.nextInt();

            //Send data to the server
            String studentData = id + "," + name + "," + m1 + "," + m2 + "," + m3;
            output.writeUTF(studentData);
            
            output.close();
            socket.close();
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
