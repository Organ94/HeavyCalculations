import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final String HOST = "localhost";


    public static void main(String[] args) {

        try (Socket socket = new Socket(HOST, Server.PORT);
             PrintWriter out = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            String msg;
            System.out.println(in.readLine());

            while (true) {
                System.out.println("Введите позицию простого числа или 'end' для выхода:");
                msg = scanner.nextLine();

                if (msg.equals("end")) break;
                try {
                    int number = Integer.parseInt(msg);
                    out.println(number);
                } catch (NumberFormatException e) {
                    System.out.println("Что-то пошло не так... Попробуй еще раз!");
                    continue;
                }
                out.flush();
                System.out.println("SERVER: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

