package com.stonie;

import java.net.Socket;
import java.util.Date;

public class IOClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 8000);
        while (true) {
            socket.getOutputStream().write((new Date() + ": hello world").getBytes());
            socket.getOutputStream().flush();
            Thread.sleep(2000);
        }
    }
}
