package md.utm.fcim.sender;

import com.google.gson.Gson;
import md.utm.fcim.common.Mesaj;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to Sender");
        Mesaj mes;
        while (true) {
            if (!(true)) break;
            try (Socket fromserver = new Socket("localhost", 4445)) {
                PrintWriter out = new PrintWriter(fromserver.getOutputStream(), true);
                Gson gson = new Gson();
                String jmes;
                // Random randomGenerator = new Random();
                //	int randomInt = randomGenerator.nextInt(100);
                Scanner scanner = new Scanner(System.in);
                String mymes = (scanner.next());
                mes = new Mesaj("send", mymes);
                jmes = gson.toJson(mes);
                out.println(jmes);
            }
        }
    }
}
