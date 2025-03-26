package main.java.org.example.homework;

import java.io.*;
import java.net.*;
import java.util.Objects;
import java.util.concurrent.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 7182;

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> timeoutFuture = null;

        try (Socket socket = new Socket(serverAddress, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Подключено к серверу.");

            // ===== 🔐 АВТОРИЗАЦИЯ =====
            System.out.print(in.readLine()); // Введите логин:
            String login = consoleInput.readLine();
            out.println(login);

            System.out.print(in.readLine()); // Введите пароль:
            String password = consoleInput.readLine();
            out.println(password);

            String authResponse = in.readLine();
            System.out.println("Сервер: " + authResponse);
            if (authResponse.startsWith("Ошибка")) {
                return;
            }
            // ==========================

            Runnable timeoutTask = () -> {
                System.out.println("⏱ Тайм-аут. Нет ответа от сервера 30 секунд.");
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };

            timeoutFuture = scheduler.schedule(timeoutTask, 30, TimeUnit.SECONDS);

            String clientMessage;
            String serverMessage;

            while (true) {
                System.out.print("Клиент: ");
                clientMessage = consoleInput.readLine();
                out.println(clientMessage);

                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Клиент завершил соединение.");
                    break;
                }

                serverMessage = in.readLine();

                if (timeoutFuture != null && !timeoutFuture.isDone()) {
                    timeoutFuture.cancel(false);
                }
                timeoutFuture = scheduler.schedule(timeoutTask, 30, TimeUnit.SECONDS);

                if (Objects.isNull(serverMessage) || serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Сервер завершил соединение.");
                    break;
                }

                System.out.println("Сервер: " + serverMessage);
            }

        } catch (IOException e) {
            System.out.println("🔌 Соединение прервано.");
        } finally {
            scheduler.shutdown();
        }
    }
}
