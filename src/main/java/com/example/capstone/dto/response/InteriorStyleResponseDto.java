package com.example.capstone.dto.response;

import com.example.capstone.entity.interior.InteriorStyle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InteriorStyleResponseDto {
    private String style;
    private List<String> about;
    private List<String> tips;
    private List<String> color;
    private List<String> interiorImage;

    /**
     * InteriorStyle 엔티티 클래스를 InteriorStyleResponseDto로 변환
     */
    public static InteriorStyleResponseDto toDto(InteriorStyle interiorStyle) {
        return InteriorStyleResponseDto.builder()
                .style(interiorStyle.getStyle())
                .about(interiorStyle.getAbout())
                .tips(interiorStyle.getTips())
                .color(interiorStyle.getColor())
                .interiorImage(interiorStyle.getInteriorImage())
                .build();
    }
}
