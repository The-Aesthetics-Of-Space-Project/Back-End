package com.example.capstone.entity.community.general.article;

import com.example.capstone.dto.request.GeneralPostUpdateRequestDto;
import com.example.capstone.entity.community.general.comment.GeneralComment;
import com.example.capstone.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"comments", "likes"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = true) // DB 테스트를 위해 임시로 false->true 변경
    private Blob thumbnail;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "nickname", referencedColumnName = "nickname")
    private User user;

    /**
     * Board와 BoardLike 사이의 일대다 관계. 'Board' 필드를 통해 연결됨.
     * Fetch.EAGER로 즉시 로드
     * 좋아요는 게시물 목록 조회, 상세 조회에서 항상 노출
     * cascade = CascadeType.REMOVE -> 부모 엔티티 삭제 시 자식 엔티티 자동으로 삭제
     * 게시글 삭제 -> 좋아요, 이미지, 스크랩, 댓글 삭제
     */
    @OneToMany(mappedBy = "generalPost", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<GeneralLike> likes;

    @OneToMany(mappedBy = "generalPost", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<GeneralImage> images;

    @OneToMany(mappedBy = "generalPost", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Scrap> scraps;

    @OneToMany(mappedBy = "generalPost", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<GeneralComment> comments;

    public void updatePost(GeneralPostUpdateRequestDto generalPostUpdateRequestDto) {
        this.title = generalPostUpdateRequestDto.getTitle();
        this.thumbnail = generalPostUpdateRequestDto.getThumbnail();
        this.content = generalPostUpdateRequestDto.getContent();
    }
}
