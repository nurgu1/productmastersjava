package main.java.org.example.homework;

import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.*;

public class Server {
    public static void main(String[] args) {
        int port = 7182;

        Map<String, String> users = new HashMap<>();
        users.put("user1", "1234");
        users.put("admin", "adminpass");

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> timeoutFuture = null;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер включен, ждем соединения...");

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

                System.out.println("Клиент подключился: " + clientSocket.getInetAddress());

                // ===== 🔐 АВТОРИЗАЦИЯ =====
                out.println("Введите логин:");
                String login = in.readLine();

                out.println("Введите пароль:");
                String password = in.readLine();

                if (!users.containsKey(login) || !users.get(login).equals(password)) {
                    out.println("Ошибка авторизации. Соединение закрыто.");
                    clientSocket.close();
                    return;
                }

                out.println("Добро пожаловать, " + login + "!");
                // ==========================

                // Задача по таймауту
                Runnable timeoutTask = () -> {
                    System.out.println("⏱ Тайм-аут. Нет сообщений от клиента 30 секунд.");
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };

                timeoutFuture = scheduler.schedule(timeoutTask, 30, TimeUnit.SECONDS);

                String clientMessage;
                String serverMessage;

                while (true) {
                    clientMessage = in.readLine();

                    if (timeoutFuture != null && !timeoutFuture.isDone()) {
                        timeoutFuture.cancel(false);
                    }
                    timeoutFuture = scheduler.schedule(timeoutTask, 30, TimeUnit.SECONDS);

                    if (clientMessage == null || clientMessage.equalsIgnoreCase("exit")) {
                        System.out.println("Клиент отключился: " + clientSocket.getInetAddress());
                        break;
                    }

                    System.out.println("Клиент: " + clientMessage);

                    if (clientMessage.equalsIgnoreCase("/time")) {
                        serverMessage = LocalTime.now().toString();
                        out.println(serverMessage);
                        continue;
                    }

                    System.out.print("Сервер: ");
                    serverMessage = consoleInput.readLine();
                    out.println(serverMessage);

                    if (serverMessage.equalsIgnoreCase("exit")) {
                        System.out.println("Завершаем соединение...");
                        break;
                    }
                }

            } catch (IOException e) {
                System.out.println("Соединение было закрыто.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scheduler.shutdown();
        }
    }
}
