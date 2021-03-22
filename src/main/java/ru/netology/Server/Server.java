package ru.netology.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static PrintWriter out; // поток записи в сокет
    public static final int SERVER_PORT = 7777;

    public static void main(String[] args) {

        try {
            try {
                server = new ServerSocket(SERVER_PORT); // серверсокет прослушивает порт 7777

                System.out.println("Сервер запущен!"); // хорошо бы серверу
                //   объявить о своем запуске

                clientSocket = server.accept(); // accept() будет ждать пока
                //кто-нибудь не захочет подключиться

                try {
                    // установив связь и воссоздав сокет для общения с клиентом можно перейти
                    // к созданию потоков ввода/вывода.
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // принимать
                    out = new PrintWriter(clientSocket.getOutputStream(), true); // отправлять

                    final String message = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                    System.out.println(message);

                    out.write("Привет, это Сервер! Подтверждаю, вы написали : " + message + "\n"); // не долго думая отвечает клиенту
                    out.flush(); // выталкиваем все из буфера

                } finally {
                    // в любом случае сокет будет закрыт
                    clientSocket.close();
                    // потоки тоже хорошо бы закрыть
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт");
                server.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

