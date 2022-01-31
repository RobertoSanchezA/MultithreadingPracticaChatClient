package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Socket socket = null;
        socket = new Socket("localhost", 8081);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());

        try{
            oos.writeObject("Juan");
            System.out.println((String) ois.readObject());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) oos.close();
            if (ois != null) ois.close();
            if (socket != null) socket.close();
            System.out.println("Conexion cerrada");
        }
    }
}
