package co.kr.st_planet.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId; // 리뷰 ID (PK)

    private Long productId; // 상품 ID (FK)

    private String email; // 사용자 ID (FK)

    private Integer star; // 평점 (1~5)

    private String reviewTitle; // 리뷰 제목

//    @Column(columnDefinition = "TEXT")
    private String reviewContent; // 리뷰 내용

    private LocalDateTime reviewCreatedAt; // 리뷰 등록일

    private LocalDateTime reviewUpdatedAt; // 리뷰 수정일

//    @PrePersist
    public void onCreate() {
        reviewCreatedAt = LocalDateTime.now(); // 등록일 설정
        reviewUpdatedAt = LocalDateTime.now(); // 수정일 설정
    }

//    @PreUpdate
    public void onUpdate() {
        reviewUpdatedAt = LocalDateTime.now(); // 수정일 업데이트
    }
}