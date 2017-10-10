package md.utm.fcim.broker;

import md.utm.fcim.broker.mq.PadMQ;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PadMQ padMQ = new PadMQ(4445);
        padMQ.start();
    }
}

