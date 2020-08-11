package com.example.demo.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IOTest {
    Socket socket = new Socket();
    InputStream inputStream = socket.getInputStream();
    OutputStream outputStream = socket.getOutputStream();

    public IOTest() throws IOException {

    }

}
