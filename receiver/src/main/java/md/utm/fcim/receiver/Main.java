package md.utm.fcim.receiver;

import md.utm.fcim.common.Mesaj;
import md.utm.fcim.common.converter.JsonConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Welcome to receiver");
        while (true) {
            if (!(true)) break;
            try (Socket fromserver = new Socket("localhost", 4445)) {
                BufferedReader in = new
                        BufferedReader(new
                        InputStreamReader(fromserver.getInputStream()));
                PrintWriter out = new
                        PrintWriter(fromserver.getOutputStream(), true);
                Mesaj mes = new Mesaj("read", "");
                out.println(JsonConverter.converterToJson(mes));
                String input = in.readLine();
                if (input != null) {
                    System.out.println(input);
                }
            }
            Thread.sleep(100);
        }
    }
}
