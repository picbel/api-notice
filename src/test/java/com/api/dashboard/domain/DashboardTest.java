package com.api.dashboard.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DashboardTest {

    @Test
    void isShow() {

        //given
        Dashboard dashboard = Dashboard.builder()
                .startDate(LocalDate.now().minusDays(1))
                .endDate(LocalDate.now().plusMonths(1))
                .build();

        //when
        boolean show = dashboard.isShow();

        //then
        assertThat(show).isTrue();
    }

    @Test
    void isShow2() {

        //given
        Dashboard dashboard = Dashboard.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(1))
                .build();

        //when
        boolean show = dashboard.isShow();

        //then
        assertThat(show).isTrue();
    }

    @Test
    void isShow3() {

        //given
        Dashboard dashboard = Dashboard.builder()
                .startDate(LocalDate.now().minusDays(10))
                .endDate(LocalDate.now().minusDays(1))
                .build();

        //when
        boolean show = dashboard.isShow();

        //then
        assertThat(show).isFalse();
    }
}