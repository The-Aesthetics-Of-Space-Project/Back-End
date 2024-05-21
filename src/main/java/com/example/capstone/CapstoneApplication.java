package com.example.capstone;

import com.example.capstone.entity.interior.InteriorStyle;
import com.example.capstone.repository.InteriorStyleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@SpringBootApplication
public class CapstoneApplication {
	public static void main(String[] args) {
		SpringApplication.run(CapstoneApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// 애플리케이션의 전역 시간대를 UTC로 설정
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@Bean
	CommandLineRunner initData(InteriorStyleRepository interiorStyleRepository) {
		return args -> {
			List<InteriorStyle> defaultStyles = Arrays.asList(
					InteriorStyle.builder()
							.styleId(1) // 수동으로 할당
							.style("모던")
							.about(Arrays.asList(
									"복잡하고 어지러운건 딱 질색, 심플한 기본이 최고!",
									"특정한 스타일에 얽매이지 않고, 간결하면서 실용성을 강조하시는 군요!",
									"심플함과 세련미를 추구하고, 쉽게 질리는 재질이나 소품은 눈이 가지 않아요"
							))
							.tips(Arrays.asList(
									"철, 유리, 소재, 모노톤, 무채색, 아이보리와 같은 키워드를 잊지마세요!",
									"단순한 것을 좋아하지만 가끔은 선명한 포인트 컬러를 주면 기분 UP",
									"액자나 식물로 인테리어 포인트를 주는 건 좋은 시작이 될거예요!"
							))
							.color(Arrays.asList(
									"/api/general/post/image/1",
									"/api/general/post/image/2",
									"/api/general/post/image/3"
							))
							.interiorImage(Arrays.asList(
									"/api/general/post/image/1",
									"/api/general/post/image/2",
									"/api/general/post/image/3"
							))
							.build(),

					InteriorStyle.builder()
							.styleId(2) // 수동으로 할당
							.style("Industrial")
							.about(Arrays.asList(
									"노출된 벽돌과 철재로 인한 거친 느낌을 좋아하시는군요!",
									"빈티지와 현대적 요소가 조화된 인테리어를 선호하시는 것 같아요.",
									"강한 개성과 독특한 분위기를 즐기시는 것 같아요."
							))
							.tips(Arrays.asList("Tip 3", "Tip 4"))
							.color(Arrays.asList("Color 3", "Color 4"))
							.interiorImage(Arrays.asList("Image URL 3", "Image URL 4"))
							.build(),

					InteriorStyle.builder()
							.styleId(3) // 수동으로 할당
							.style("Scandinavian")
							.about(Arrays.asList(
									"밝고 차분한 색상을 좋아하시고, 실용성을 중시하는 스타일이군요!",
									"자연스러운 소재와 심플한 디자인을 선호하시는 것 같아요.",
									"따뜻하고 아늑한 분위기를 좋아하시는군요."
							))
							.tips(Arrays.asList("Tip 5", "Tip 6"))
							.color(Arrays.asList("Color 5", "Color 6"))
							.interiorImage(Arrays.asList("Image URL 5", "Image URL 6"))
							.build(),

					InteriorStyle.builder()
							.styleId(4) // 수동으로 할당
							.style("Bohemian")
							.about(Arrays.asList(
									"자유롭고 개성 넘치는 스타일을 좋아하시는군요!",
									"다양한 색상과 패턴을 조화롭게 사용하는 것을 선호하시네요.",
									"편안하고 자연스러운 분위기를 좋아하시는 것 같아요."
							))
							.tips(Arrays.asList("Tip 7", "Tip 8"))
							.color(Arrays.asList("Color 7", "Color 8"))
							.interiorImage(Arrays.asList("Image URL 7", "Image URL 8"))
							.build(),

					InteriorStyle.builder()
							.styleId(5) // 수동으로 할당
							.style("Vintage")
							.about(Arrays.asList(
									"고전적이고 우아한 분위기를 좋아하시는군요!",
									"시간의 흐름을 담은 소품과 가구를 선호하시는 것 같아요.",
									"독특하고 매력적인 인테리어를 좋아하시네요."
							))
							.tips(Arrays.asList("Tip 9", "Tip 10"))
							.color(Arrays.asList("Color 9", "Color 10"))
							.interiorImage(Arrays.asList("Image URL 9", "Image URL 10"))
							.build()
			);

			for (InteriorStyle style : defaultStyles) {
				Optional<InteriorStyle> existingStyle = interiorStyleRepository.findByStyle(style.getStyle());
				if (!existingStyle.isPresent()) {
					interiorStyleRepository.save(style);
				}
			}
		};
	}
}