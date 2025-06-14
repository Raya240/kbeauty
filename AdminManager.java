package kbeauty;

import java.io.*;
import java.util.Scanner;

public class AdminManager {

    public static void showAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n📊 관리자 메뉴");
            System.out.println("1. 로그 보기");
            System.out.println("2. 로그 삭제");
            System.out.println("3. 종료");
            System.out.print("선택: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    viewLogs();
                    break;
                case "2":
                    deleteLogs();
                    break;
                case "3":
                    System.out.println("🔒 관리자 모드 종료.");
                    return;
                default:
                    System.out.println("❌ 잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }

    private static void viewLogs() {
        File logFile = new File("kbeauty_log.txt");
        if (!logFile.exists()) {
            System.out.println("⚠️ 로그 파일이 존재하지 않습니다.");
            return;
        }

        System.out.println("\n📄 로그 내용:");
        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("⚠️ 로그 파일을 읽는 중 오류 발생.");
        }
    }

    private static void deleteLogs() {
        File logFile = new File("kbeauty_log.txt");
        if (logFile.exists() && logFile.delete()) {
            System.out.println("🗑️ 로그 파일이 삭제되었습니다.");
        } else {
            System.out.println("⚠️ 로그 파일 삭제에 실패했습니다.");
        }
    }
}
