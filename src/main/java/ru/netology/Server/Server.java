package ru.netology.Server;

import ru.netology.MyClosable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int SERVER_PORT = 7777;

    public static void main(String[] args) {

        System.out.println("Сервер запущен");
        try (MyClosable myClosable = new MyClosable();
             ServerSocket server = new ServerSocket(SERVER_PORT); // серверсокет прослушивает порт 7777
             Socket clientSocket = server.accept(); // ожидаем подключение клиента
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // принимаем
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) // отправляем
        {
            final String message = in.readLine(); // ждём пока клиент что-нибудь нам напишет
            System.out.println(message);
            out.write("Привет, это Сервер! Подтверждаю, вы написали : " + message + "\n"); // не долго думая отвечает клиенту
            out.flush(); // выталкиваем все из буфера.
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}




