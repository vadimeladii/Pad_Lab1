package md.utm.fcim.broker;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in;
        PrintWriter out;
        Queue<String> queue = new ConcurrentLinkedQueue<String>();
        System.out.println("----->Welcome to Broker<-----");
        System.out.println("1. Startup Broker");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number");
        Integer number = sc.nextInt();
        switch (number) {
            case 1:
                try (ServerSocket serverSocket = new ServerSocket(4445)) {
                    System.out.print("Waiting for a client...");
                    boolean isStopped = false;
                    while (!isStopped) {
                        try (Socket clientSocket = serverSocket.accept()) {
                            //do something with clientSocket
                            in = new BufferedReader(new
                                    InputStreamReader(clientSocket.getInputStream()));
                            String input = in.readLine();
                            JSONObject jObject = new JSONObject(input);
                            String command = jObject.getString("command");

                            if (command.equals("send")) {
                                queue.add(input);
                                System.out.println("measaj " + input);
                            } else if (command.equals("read")) {
                                out = new PrintWriter(clientSocket.getOutputStream(), true);
                                if (!queue.isEmpty()) {
                                    out.println(queue.poll());

                                }

                            }
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
}
