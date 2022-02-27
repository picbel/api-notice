package com.api.dashboard.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class Dashboard {
    Long id;
    String title;
    String content;
    LocalDate startDate;
    LocalDate endDate;
    LocalDateTime createDate;
    Integer viewCount;
    List<String> attachments = new ArrayList<>(); // 첨부 파일
    String writer;

    public boolean isShow(){
        LocalDate now = LocalDate.now();
        return isStart(now) && isNotEnd(now);
    }

    private boolean isStart(LocalDate now){
        return now.equals(startDate) || now.isAfter(startDate);
    }

    private boolean isEnd(LocalDate now){
        return now.isAfter(endDate);
    }

    private boolean isNotEnd(LocalDate now){
        return !isEnd(now);
    }


    // 대시보드 - 대시보드 조회수 mapsid
    // 첨부파일은 1대N 관계
}
