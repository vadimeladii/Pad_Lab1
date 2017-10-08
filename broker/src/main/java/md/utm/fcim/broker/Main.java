package md.utm.fcim.broker;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PadMQ padMQ = new PadMQ(4445);
        padMQ.start();
    }
}

