package com.api.dashboard.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NoticeTest {

    @DisplayName("공지사항 노출 검증")
    @Test
    void isShow() {

        //given
        Notice notice = Notice.builder()
                .startDate(LocalDate.now().minusDays(1))
                .endDate(LocalDate.now().plusMonths(1))
                .build();

        //when
        boolean show = notice.isShow();

        //then
        assertThat(show).isTrue();
    }

    @DisplayName("공지사항 노출 검증 / 시작 날짜와 겹칠때")
    @Test
    void isShow2() {

        //given
        Notice notice = Notice.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(1))
                .build();

        //when
        boolean show = notice.isShow();

        //then
        assertThat(show).isTrue();
    }

    @DisplayName("공지사항 노출 검증 / 공지사항 노출 실패")
    @Test
    void isShow3() {

        //given
        Notice notice = Notice.builder()
                .startDate(LocalDate.now().minusDays(10))
                .endDate(LocalDate.now().minusDays(1))
                .build();

        //when
        boolean show = notice.isShow();

        //then
        assertThat(show).isFalse();
    }
}