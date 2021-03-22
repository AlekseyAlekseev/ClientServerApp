package ru.netology.Client;

import ru.netology.Server.Server;

import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static PrintWriter out; // поток записи в сокет


    public static void main(String[] args) {

        try {
            try {
                // адрес - локальный хост, порт - 4004, такой же как у сервера
                clientSocket = new Socket("localhost", Server.SERVER_PORT);

                //  у сервера доступ на соединение
                reader = new BufferedReader(new InputStreamReader(System.in));

                // читать соообщения с сервера
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // писать туда же
                out = new PrintWriter(clientSocket.getOutputStream(), true); // отправлять


                System.out.println("Введите сообщение для сервера:");
                // если соединение произошло и потоки успешно созданы - мы можем
                //  работать дальше и предложить клиенту что то ввести
                // если нет - вылетит исключение

                String message = reader.readLine(); // ждём пока клиент что-нибудь

                // не напишет в консоль
                out.write(message + "\n"); // отправляем сообщение на сервер
                out.flush(); // выталкиваем все из буфера

                String serverMessage = in.readLine(); // ждём, что скажет сервер
                System.out.println(serverMessage); // получив - выводим на экран

            } finally {
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
