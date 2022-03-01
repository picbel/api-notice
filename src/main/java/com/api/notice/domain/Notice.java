package com.api.notice.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notice {
    private Long id;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createDateTime;
    private Integer viewCount;
    private List<AttachmentFile> attachmentFiles = new ArrayList<>();
    private String writer;

    public boolean isShow(){
        return isStart() && isNotEnd();
    }

    public boolean isStart(){
        return LocalDate.now().equals(startDate) || LocalDate.now().isAfter(startDate);
    }

    public boolean isEnd(){
        return LocalDate.now().isAfter(endDate);
    }

    public boolean isNotEnd(){
        return !isEnd();
    }


}
