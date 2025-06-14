package kbeauty;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Logger {

    private static final String LOG_FILE = "kbeauty_log.txt";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void log(String message) {
        String timestamp = LocalDateTime.now().format(dtf);
        String logMessage = "[" + timestamp + "] [로그] " + message;
        System.out.println(logMessage);
        appendToFile(logMessage);
    }

    public static void logRequest(User user, String mood, List<Product> products) {
        String timestamp = LocalDateTime.now().format(dtf);
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(timestamp).append("] [요청 기록] 사용자: ").append(user.getUsername()).append(", 감정: ").append(mood).append("\n");
        for (Product p : products) {
            sb.append("  - ").append(p.toString()).append("\n");
        }
        String logEntry = sb.toString();
        System.out.print(logEntry);
        appendToFile(logEntry);
    }

    private static void appendToFile(String text) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            fw.write(text);
            fw.write(System.lineSeparator());
        } catch (IOException e) {
            System.err.println("로그 파일에 기록하는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
