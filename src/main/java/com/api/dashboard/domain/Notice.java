package com.api.dashboard.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private Long id;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createDate;
    private Integer viewCount;
    private List<String> attachmentsUrl = new ArrayList<>(); // 첨부 파일
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


    // 대시보드 - 대시보드 조회수 mapsid
    // 첨부파일은 1대N 관계
}
