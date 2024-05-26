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
									"- 복잡하고 어지러운건 딱 질색, 심플한 기본이 최고!",
									"- 특정한 스타일에 얽매이지 않고, 간결하면서 실용성을 강조하시는 군요!",
									"- 단조로움과 세련미를 추구하고, 쉽게 질리는 재질이나 소품은 눈에 가지 않아요"
							))
							.tips(Arrays.asList(
									"- 매끈한 직선이나 곡선 형태의 가구나 소품을 위주로 배치해보세요!",
									"- 철, 유리 소재, 모노톤, 무채색, 아이보리와 같은 키워드를 잊지마세요!",
									"- 단순한 것을 좋아하지만 가끔은 선명한 포인트 컬러를 주면 기분 UP",
									"- 액자나 식물로 인테리어 포인트를 주는 건 좋은 시작이 될거예요!"
							))
							.color(Arrays.asList(
									"/api/interior/style/image/moderncolor1.png",
									"/api/interior/style/image/moderncolor2.png",
									"/api/interior/style/image/moderncolor3.png"
							))
							.interiorImage(Arrays.asList(
									"/api/interior/style/image/modern1.jpg",
									"/api/interior/style/image/modern2.jpeg",
									"/api/interior/style/image/modern3.jpg"
							))
							.build(),

					InteriorStyle.builder()
							.styleId(2) // 수동으로 할당
							.style("미니멀 & 심플")
							.about(Arrays.asList(
									"- 물질적인 소유욕과 집착을 버리고 내면의 평화와 행복을 중시하시는 군요.",
									"- 빈 공간에서 느껴지는 여유로움, 그 즐거움을 아는 미니멀리스트!",
									"- 실용성을 추구, 겉으로 보이는 것들을 줄이고, 꼭 필요한 것만 보이도록!"
							))
							.tips(Arrays.asList(
									"- 불필요한 물건을 제거하고 꼭 필요한 아이템만 남겨두세요! 깔끔한 공간을 유지하기 위해 정리정돈이 중요합니다.",
									"- 장식이 적고 깔끔한 선이 돋보이는 가구로 선택! 과도한 장식은 NO!",
									"- 창문을 통해 들어오는 자연광을 최대한 활용! 밝고 개방된 느낌을 주기 위해 커튼을 가볍고 투명한 소재로 선택하세요.",
									"- 단순한 색상 팔레트 사용! 강렬한 색상은 NO!"))
							.color(Arrays.asList(
									"/api/interior/style/image/minimalcolor1.png",
									"/api/interior/style/image/minimalcolor2.png",
									"/api/interior/style/image/minimalcolor3.png"
							))
							.interiorImage(Arrays.asList(
									"/api/interior/style/image/minimal1.jpg",
									"/api/interior/style/image/minimal2.jpg",
									"/api/interior/style/image/minimal3.jpg"
							))
							.build(),

					InteriorStyle.builder()
							.styleId(3) // 수동으로 할당
							.style("내추럴")
							.about(Arrays.asList(
									"- 나무, 흙, 가죽처럼 자연에서 얻을 수 있는 자연친화적 소재를 선호해요.",
									"- 인위적으로 가공된 느낌은 NO! 소재의 거친 질감 그대로 YES!",
									"- 복잡한 건 싫죠~ 가장 쉽게 따라하고 누구나 할 수 있는 인테리어를 선호해요."
							))
							.tips(Arrays.asList(
									"- 내추럴은 곧 디테일입니다. 가구, 소품, 조명의 교체만으로도 효과가 나타나는 활용성 높은 컨셉입니다.",
									"- 공간을 더 밝고 생기 있게 끌어올리기 위한 식물 배치는 200% 필요!",
									"- 디자인이나 무늬가 과하거나 튀는 제품은 자제하시는 게 좋아요",
									"- 전체적인 컬러는 밝은 색으로 가고 무겁거나 튀는 느낌은 최대한 절제! 산뜻하고 상쾌한 색상을 조합해 보세요."
							))
							.color(Arrays.asList(
									"/api/interior/style/image/naturalcolor1.png",
									"/api/interior/style/image/naturalcolor2.png",
									"/api/interior/style/image/naturalcolor3.png"
							))
							.interiorImage(Arrays.asList(
									"/api/interior/style/image/natural1.webp",
									"/api/interior/style/image/natural2.jpg",
									"/api/interior/style/image/natural3.jpg"
							))
							.build(),

					InteriorStyle.builder()
							.styleId(4) // 수동으로 할당
							.style("러블리 & 로맨틱")
							.about(Arrays.asList(
									"- 로맨틱하고 우아한 스타일을 좋아하시는군요!",
									"- 다양한 색상과 패턴을 조화롭게 사용하는 것을 선호하시네요.",
									"- 편안하고 자연스러운 분위기를 좋아해요!."
							))
							.tips(Arrays.asList(
									"- 부드러운 색상 팔레트 사용! 핑크, 라벤더, 파스텔 블루, 크림 등의 부드러운 색상을 사용해 공간에 로맨틱한 분위기를 더하세요.",
									"- 커튼, 쿠션, 침구 등에 플로럴 패턴을 사용하여 공간에 생기를 더하세요. 촉감이 좋은 패브릭을 선택하여 더욱 포근한 분위기 연출 가능!",
									"- 곡선미가 돋보이는 우아한 가구를 선택하세요. 곡선형 헤드보드가 있는 침대는 공간에 로맨틱한 느낌을 더해줍니다!",
									"- 조명은 로맨틱한 분위기를 만드는 데 중요한 요소! 따뜻한 색상의 조명을 사용, 캔들 라이트를 활용하기!"
							))
							.color(Arrays.asList(
									"/api/interior/style/image/lovelycolor1.png",
									"/api/interior/style/image/lovelycolor2.png",
									"/api/interior/style/image/lovelycolor3.png"))
							.interiorImage(Arrays.asList(
									"/api/interior/style/image/lovely1.jpg",
									"/api/interior/style/image/lovely2.jpg",
									"/api/interior/style/image/lovely3.jpg"
							))
							.build(),

					InteriorStyle.builder()
							.styleId(5) // 수동으로 할당
							.style("빈티지 & 레트로")
							.about(Arrays.asList(
									"- 고전적이고 우아한 분위기를 좋아하시는군요!",
									"- 시간의 흐름을 담은 소품과 가구를 선호하시는 것 같아요.",
									"- 독특하고 매력적인 인테리어를 좋아해요!"
							))
							.tips(Arrays.asList(
									"- 중고 가구점이나 앤티크 샵에서 빈티지 가구와 소품을 찾아보세요!",
									"- 따뜻한 색상을 사용하여 복고풍의 분위기 연출하기! 공간을 아늑하고 친근하게 만들 수 있답니다!",
									"- 레트로 느낌의 액세서리와 장식품을 활용하여 공간에 독특한 포인트를 주세요! 빈티지 라디오, 레코드 플레이어 등을 활용하여 레트로 감성 UP",
									"- 다양한 소재를 믹스 매치하여 독특한 빈티지 스타일을 연출하세요. 나무, 금속, 유리, 패브릭 등의 소재를 조화롭게 배치하면 공간이 더욱 풍부하고 개성있게 보여요!"
							))
							.color(Arrays.asList(
									"/api/interior/style/image/vintagecolor1.png",
									"/api/interior/style/image/vintagecolor2.png",
									"/api/interior/style/image/vintagecolor3.png"))
							.interiorImage(Arrays.asList(
									"/api/interior/style/image/vintage1.jpg",
									"/api/interior/style/image/vintage2.jpg",
									"/api/interior/style/image/vintage3.jpg"
							))
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