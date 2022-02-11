package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        Socket socket = null;
        socket = new Socket("localhost", 8081);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());

        Scanner scan = new Scanner(System.in);

        try{
            //envio nombre de usuario
            System.out.println("Introduce tu nombre de usuario");
            String username = scan.nextLine();
            oos.writeObject(username);
            //recibo el mensaje del servidor de bienvenida
            String msg = "";
            boolean keepTalking = false;
            boolean justConnected = true;

            while (!keepTalking) {
                if (justConnected) {
                    System.out.println(ois.readObject());
                    justConnected = false;
                }
                //envio mensajes y compruebo que el usuario no quiere terminar
                System.out.println("Escribe el mensaje ");
                msg = scan.nextLine();

                //Envío el mensaje al servidor y espero la respuesta
                oos.writeObject(msg);

                String msgReceived = (String) ois.readObject();
                if (msgReceived.equals("good bye")) {
                    System.out.println(msgReceived);
                    keepTalking = true;
                }
            }
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
