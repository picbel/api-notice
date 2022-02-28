package com.api.dashboard.model;

import com.api.dashboard.domain.Notice;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createDate;
    private Integer viewCount;
    private List<String> attachmentsUrl = new ArrayList<>(); // 첨부 파일
    private String writer;

    public NoticeDTO(Notice notice){
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.startDate = notice.getStartDate();
        this.endDate = notice.getEndDate();
        this.createDate = notice.getCreateDate();
        this.viewCount = notice.getViewCount();
        this.attachmentsUrl = notice.getAttachmentsUrl();
        this.writer = notice.getWriter();
    }

    public Notice toDomain(ObjectMapper objectMapper) {
        return objectMapper.convertValue(this, Notice.class);
    }
}
