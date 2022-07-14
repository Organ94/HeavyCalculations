import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static final ArrayList<Integer> PRIME_NUMBERS = new ArrayList<>();
    private static final int N = 10_000;
    protected static final int PORT = 8080;

    public static void main(String[] args) {

        while (true) {
            try (ServerSocket server = new ServerSocket(PORT);
                 Socket socket = server.accept();
                 PrintWriter out = new PrintWriter(
                         socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(
                         new InputStreamReader(socket.getInputStream()))) {

                if (socket.isConnected()) {
                    out.println("Соединение установлено... \"" +
                            socket.getLocalAddress().getHostAddress() + ":" +
                            socket.getLocalPort() + "\"");
                }

                String line;
                while ((line = in.readLine()) != null) {

                    out.println("На позиции - " + line + " находится простое число - " + numb(line));

                    if ("end".equals(line)) break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int numb(String line) {
        int num = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
            if (checkSimple(i)) {
                PRIME_NUMBERS.add(i);
            }
        }
        return PRIME_NUMBERS.get(num - 1);
    }

    private static boolean checkSimple(int i) {
        if (i <= 1) {
            return false;
        } else if (i <= 3) {
            return true;
        } else if (i % 2 == 0 || i % 3 == 0) {
            return false;
        }
        int n = 5;
        while (n * n <= i) {
            if (i % n == 0 || i % (n + 2) == 0) {
                return false;
            }
            n = n + 6;
        }
        return true;
    }
}
