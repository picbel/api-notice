package com.api.dashboard.model;

import com.api.dashboard.domain.Notice;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {

    private Long id;

    @NotNull(message = "제목을 입력하여주세요")
    private String title;

    @NotNull(message = "내용을 입력하여주세요")
    private String content;

    @NotNull(message = "시작 날짜를 입력하여주세요.")
    private LocalDate startDate;

    @Future(message = "종료 날짜가 현재보다 이전입니다.")
    private LocalDate endDate;

    private LocalDateTime createDateTime;

    private Integer viewCount;

    @Valid
    private List<AttachmentFileDTO> attachmentFiles = new ArrayList<>(); // 첨부 파일
    private String writer;

    public NoticeDTO(Notice notice){
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.startDate = notice.getStartDate();
        this.endDate = notice.getEndDate();
        this.createDateTime = notice.getCreateDateTime();
        this.viewCount = notice.getViewCount();
        if (Objects.nonNull(notice.getAttachmentFiles())) {
            this.attachmentFiles = notice.getAttachmentFiles().stream().map(AttachmentFileDTO::new).collect(Collectors.toList());
        }else{
            this.attachmentFiles = new ArrayList<>();
        }
        this.writer = notice.getWriter();
    }

    public Notice toDomain(ObjectMapper objectMapper) {
        return objectMapper.convertValue(this, Notice.class);
    }
}
