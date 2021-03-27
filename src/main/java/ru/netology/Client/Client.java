package ru.netology.Client;

import ru.netology.MyClosable;
import ru.netology.Server.Server;

import java.io.*;
import java.net.Socket;

public class Client implements AutoCloseable {

    public static void main(String[] args) {

        try (// адрес - локальный хост, порт - 7777, такой же как у сервера
             Socket clientSocket = new Socket("localhost", Server.SERVER_PORT);

             //  у сервера доступ на соединение
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

             // читать соообщения с сервера
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             // писать туда же
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) // отправлять
        {
            System.out.println("Введите сообщение для сервера:");
            // если соединение произошло и потоки успешно созданы - мы можем
            //  работать дальше и предложить клиенту что то ввести
            // если нет - вылетит исключение

            String message = reader.readLine(); // ждём пока клиент что-нибудь напишет в консоль
            out.write(message + "\n"); // отправляем сообщение на сервер
            out.flush(); // выталкиваем все из буфера
            String serverMessage = in.readLine(); // ждём, что скажет сервер
            System.out.println(serverMessage); // получив - выводим на экран
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("Клиент выключен");
    }
}
