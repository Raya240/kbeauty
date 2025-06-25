package kbeauty;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuthManager authManager = new AuthManager();
        ProductRecommender recommender = new ProductRecommender();
        List<Product> cart = new ArrayList<>();

        System.out.println("🎀 K-Beauty Mood 로그인 시스템 🎀");

        User currentUser = null;

        while (currentUser == null) {
            System.out.print("아이디: ");
            String username = scanner.nextLine();
            System.out.print("비밀번호: ");
            String password = scanner.nextLine();
            currentUser = authManager.login(username, password);

            if (currentUser == null) {
                System.out.println(" 로그인 실패. 다시 시도하세요.");
            } else {
                System.out.println(" 로그인 성공! 환영합니다, " + currentUser.getUsername() + "!");
                Logger.log("🔓 " + currentUser.getUsername() + "님이 시스템에 로그인했습니다");
            }
        }

        while (true) {
            System.out.println("\n==============================");
            System.out.println("1. 화장품 추천받기");
            System.out.println("2. 로그아웃");
            System.out.println("3. 장바구니 보기");
            System.out.println("4. 장바구니 상품 구매하기");
            System.out.print("선택: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                List<String> moods = recommender.getAllMoods();
                for (int i = 0; i < moods.size(); i++) {
                    System.out.println((i + 1) + ". " + moods.get(i));
                }
                System.out.print("번호를 입력하세요: ");
                int index = Integer.parseInt(scanner.nextLine()) - 1;
                if (index < 0 || index >= moods.size()) {
                    System.out.println(" 잘못된 선택입니다.");
                    continue;
                }

                String mood = moods.get(index);
                List<Product> recs = recommender.recommend(mood);
                if (recs.isEmpty()) {
                    System.out.println("추천 제품이 없습니다.");
                } else {
                    for (int i = 0; i < recs.size(); i++) {
                        System.out.println((i + 1) + ". " + recs.get(i));
                    }
                    Logger.logRequest(currentUser, mood, recs);

                    System.out.print("🛒 장바구니에 추가할 제품 번호 (엔터 건너뜀): ");
                    String input = scanner.nextLine();
                    if (!input.isEmpty()) {
                        int sel = Integer.parseInt(input) - 1;
                        if (sel >= 0 && sel < recs.size()) {
                            cart.add(recs.get(sel));
                            System.out.println(" 장바구니에 추가되었습니다!");
                        } else {
                            System.out.println(" 잘못된 번호입니다.");
                        }
                    }
                }

            } else if (choice.equals("2")) {
                System.out.println("안녕히 가세요!");
                Logger.log("🔒 " + currentUser.getUsername() + "님이 로그아웃했습니다.");
                break;

            } else if (choice.equals("3")) {
                System.out.println("==============================");
                System.out.println(" 당신의 장바구니:");
                if (cart.isEmpty()) {
                    System.out.println("장바구니가 비어 있습니다.");
                } else {
                    for (Product p : cart) System.out.println("- " + p);
                }
                System.out.println("==============================");

            } else if (choice.equals("4")) {
                PaymentProcessor.purchaseCartAll(cart, scanner);

            } else {
                System.out.println("잘못된 메뉴입니다.");
            }
        }

        scanner.close();
    }
}
