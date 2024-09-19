package co.kr.st_planet.returnReason.entity;

import lombok.Data;

@Data
public class ReturnReason {
    private String reasonId;          // 반품/교환 사유 ID (Primary Key)
    private String reasonContent;     // 반품/교환 사유 내용
}