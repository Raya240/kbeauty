package kbeauty;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthManager authManager = new AuthManager();
        ProductRecommender recommender = new ProductRecommender();
        List<Product> cart = new ArrayList<>(); // 🛒 장바구니

        System.out.println("🎀 K-Beauty Mood 로그인 시스템 🎀");

        User currentUser = null;

        // 🔐 Login loop
        while (currentUser == null) {
            System.out.print("아이디: ");
            String username = scanner.nextLine();
            System.out.print("비밀번호: ");
            String password = scanner.nextLine();

            currentUser = authManager.login(username, password);

            if (currentUser == null) {
                System.out.println("❌ 로그인 실패. 다시 시도하세요.");
            } else {
                System.out.println("✅ 로그인 성공! 환영합니다, " + currentUser.getUsername() + "!");
                Logger.log("🔓 " + currentUser.getUsername() + "님이 시스템에 로그인했습니다");

                if (currentUser.getUsername().equals("admin")) {
                    AdminManager.showAdminMenu();
                    return; // 관리자 기능 종료 후 프로그램 종료
                }
            }
        }

        // 📦 Main menu
        while (true) {
            System.out.println("\n==============================");
            System.out.println("1. 화장품 추천받기");
            System.out.println("2. 로그아웃");
            System.out.println("3. 장바구니 보기");
            System.out.print("선택: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                // 🔢 감정 선택 메뉴
                List<String> moods = Arrays.asList("스트레스", "기쁨", "피로", "영감", "슬픔", "자신감", "불안", "에너지", "설렘", "혼란", "외로움");

                System.out.println("감정을 선택하세요:");
                for (int i = 0; i < moods.size(); i++) {
                    System.out.println((i + 1) + ". " + moods.get(i));
                }
                System.out.print("번호를 입력하세요: ");
                int moodIndex = -1;
                try {
                    moodIndex = Integer.parseInt(scanner.nextLine()) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("숫자를 입력하세요.");
                    continue;
                }

                if (moodIndex < 0 || moodIndex >= moods.size()) {
                    System.out.println("잘못된 번호입니다.");
                    continue;
                }

                String mood = moods.get(moodIndex);

                List<Product> recommendations = recommender.recommend(mood);
                if (recommendations.isEmpty()) {
                    System.out.println("추천할 제품이 없습니다.");
                } else {
                    System.out.println("추천 제품:");
                    for (int i = 0; i < recommendations.size(); i++) {
                        System.out.println((i + 1) + ". " + recommendations.get(i).toString());
                    }
                    Logger.logRequest(currentUser, mood, recommendations);
                    System.out.println("(로그가 저장되었습니다.)");

                    System.out.print("🛒 장바구니에 추가할 제품 번호 (건너뛰려면 엔터): ");
                    String cartInput = scanner.nextLine();
                    if (!cartInput.isEmpty()) {
                        try {
                            int cartIndex = Integer.parseInt(cartInput) - 1;
                            if (cartIndex >= 0 && cartIndex < recommendations.size()) {
                                cart.add(recommendations.get(cartIndex));
                                System.out.println("✅ 장바구니에 추가되었습니다!");
                            } else {
                                System.out.println("❌ 유효하지 않은 번호입니다.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("숫자를 입력해주세요.");
                        }
                    }
                }
            } else if (choice.equals("2")) {
                Logger.log("🔒 " + currentUser.getUsername() + "님이 시스템에서 로그아웃했습니다.");
                System.out.println("안녕히 가세요!");
                break;
            } else if (choice.equals("3")) {
                System.out.println("==============================");
                System.out.println("🛒 당신의 장바구니:");
                if (cart.isEmpty()) {
                    System.out.println("장바구니가 비어 있습니다.");
                } else {
                    for (Product p : cart) {
                        System.out.println("- " + p.toString());
                    }
                }
                System.out.println("==============================");
            } else {
                System.out.println("잘못된 선택입니다.");
            }
        }

        scanner.close();
    }
}
