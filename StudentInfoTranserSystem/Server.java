package server;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    // Array to store student names
    static ArrayList<String> studentNames = new ArrayList<>();

    // HashMap to store student ID and Marks
    static HashMap<Integer, int[]> studentRecords = new HashMap<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                DataInputStream input = new DataInputStream(socket.getInputStream());
                String data = input.readUTF();

                String[] parts = data.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int m1 = Integer.parseInt(parts[2]);
                int m2 = Integer.parseInt(parts[3]);
                int m3 = Integer.parseInt(parts[4]);

                studentNames.add(name);
                studentRecords.put(id, new int[]{m1, m2, m3});

                //Display all student records
                System.out.println("\n--- Student Records ---");
                for (int sid : studentRecords.keySet()) {
                    int[] marks = studentRecords.get(sid);
                    String sname = studentNames.get(new ArrayList<>(studentRecords.keySet()).indexOf(sid));
                    System.out.println("ID: " + sid + ", Name: " + sname +
                            ", Marks: " + marks[0] + ", " + marks[1] + ", " + marks[2]);
                }
                input.close();
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
