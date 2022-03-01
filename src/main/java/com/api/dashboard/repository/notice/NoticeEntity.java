package com.api.dashboard.repository.notice;

import com.api.dashboard.domain.Notice;
import com.api.dashboard.repository.BaseTimestamp;
import com.api.dashboard.repository.attachments.AttachmentFileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notice")
@Entity
@DynamicUpdate
public class NoticeEntity extends BaseTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer viewCount;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "noticeEntity", cascade = CascadeType.ALL)
    private List<AttachmentFileEntity> attachmentFileEntities = new ArrayList<>();

    private String writer;

    public NoticeEntity(Notice notice){
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.startDate = notice.getStartDate();
        this.endDate = notice.getEndDate();
        this.viewCount = notice.getViewCount();
        if (Objects.nonNull(notice.getAttachmentFiles())) {
            this.attachmentFileEntities = notice.getAttachmentFiles().stream()
                    .map(attachmentFile -> new AttachmentFileEntity(attachmentFile, this))
                    .collect(Collectors.toList());
        }else{
            this.attachmentFileEntities = new ArrayList<>();
        }
        this.writer = notice.getWriter();
    }

    public Notice toDomain() {
        return Notice.builder()
                .id(id)
                .title(title)
                .content(content)
                .startDate(startDate)
                .endDate(endDate)
                .viewCount(viewCount)
                .attachmentFiles(attachmentFileEntities.stream()
                        .map(AttachmentFileEntity::toDomain)
                        .collect(Collectors.toList())
                )
                .createDateTime(getCreateDateTime())
                .writer(writer)
                .build();
    }

    public NoticeEntity update(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.startDate = notice.getStartDate();
        this.endDate = notice.getEndDate();
        this.viewCount = notice.getViewCount();
        if (Objects.nonNull(notice.getAttachmentFiles())) {
            this.attachmentFileEntities = notice.getAttachmentFiles().stream()
                    .map(attachmentFile -> new AttachmentFileEntity(attachmentFile, this))
                    .collect(Collectors.toList());
        }else{
            this.attachmentFileEntities = new ArrayList<>();
        }
        this.writer = notice.getWriter();

        return this;
    }
}
