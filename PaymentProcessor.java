package kbeauty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PaymentProcessor {
    public static void purchaseCartAll(List<Product> cart, Scanner scanner) {
        if (cart.isEmpty()) {
            System.out.println("장바구니가 비어 있습니다.");
            return;
        }

        System.out.println("\n=== 장바구니 전체 결제 시작 ===");
        System.out.print("카드 번호를 입력하세요 (예: 1111-1111): ");
        String cardNumber = scanner.nextLine();
        System.out.print("카드 비밀번호를 입력하세요 (4자리): ");
        String cardPin = scanner.nextLine();

        if (!cardNumber.equals("1111-1111") || !cardPin.equals("1234")) {
            System.out.println("❌ 결제 오류: 카드 정보가 올바르지 않습니다.");
            Logger.log("장바구니 전체 결제 실패");
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println("\n========= 영수증 =========");
        System.out.println("일시    : " + formattedDate);
        System.out.println("결제자  : 카드번호 " + maskCard(cardNumber));
        System.out.println("--------------------------");

        int total = 0;
        for (Product p : cart) {
            System.out.println("- " + p.toString());
            total += p.getPrice();
            Logger.log("상품 구매 완료: " + p.getName() + " (" + p.getPrice() + "원)");
        }

        System.out.println("--------------------------");
        System.out.println("총 금액 : " + total + "원");
        System.out.println("        감사합니다!        ");
        System.out.println("==========================\n");

        cart.clear(); // 결제 완료 후 장바구니 비우기
    }

    private static String maskCard(String card) {
        if (card.length() == 9) return card.substring(0, 5) + "****";
        return card;
    }
}
