package kbeauty;

import java.util.*;

public class ProductRecommender {
    private List<Product> products = new ArrayList<>();

    public ProductRecommender() {
        // 기본 추천 제품
        products.add(new Product("병풀 마스크", "스킨케어", "스트레스"));
        products.add(new Product("밝은 색 틴트", "메이크업", "기쁨"));
        products.add(new Product("알로에 젤", "보습", "피로"));
        products.add(new Product("파스텔 아이섀도", "메이크업", "영감"));

        // 확장된 감정에 대한 제품
        products.add(new Product("카모마일 토너", "스킨케어", "불안"));
        products.add(new Product("레드 립스틱", "메이크업", "자신감"));
        products.add(new Product("라벤더 바디로션", "바디케어", "슬픔"));
        products.add(new Product("시트러스 샤워젤", "바디케어", "에너지"));
        products.add(new Product("광채 메이크업 베이스", "메이크업", "설렘"));
        products.add(new Product("부드러운 BB크림", "메이크업", "로맨틱"));
        products.add(new Product("민트 클렌징 폼", "스킨케어", "혼란"));
        products.add(new Product("따뜻한 립밤", "보습", "외로움"));
    }

    public List<Product> recommend(String mood) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getMood().equalsIgnoreCase(mood)) {
                result.add(p);
            }
        }
        return result;
    }
}
