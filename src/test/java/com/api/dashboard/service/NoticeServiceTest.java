package com.api.dashboard.service;

import com.api.dashboard.domain.Notice;
import com.api.dashboard.fixture.NoticeRepositoryResolver;
import com.api.dashboard.model.NoticeDTO;
import com.api.dashboard.repository.NoticeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(NoticeRepositoryResolver.class)
class NoticeServiceTest {

    private NoticeService noticeService;

    @BeforeEach
    void setUp(NoticeRepository noticeRepository) {
        noticeService = new NoticeServiceImpl(noticeRepository, new ObjectMapper());
    }

    @Test
    void register() {
        //given
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .title("공지사항 제목 1")
                .content("공지사항 내용 1")
                .build();

        //when
        Notice register = noticeService.register(noticeDTO);

        //then
        assertThat(register.getId()).isNotNull();

    }

    @Test
    void modify() {
        //given
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .id(1L)
                .title("공지사항 제목 수정")
                .content("공지사항 내용 수정")
                .build();

        //when
        Notice modify = noticeService.modify(noticeDTO);

        //then
        assertThat(modify)
                .extracting("id", "title", "content")
                .containsExactly(1L, "공지사항 제목 수정", "공지사항 내용 수정");
    }

    @Test
    void delete() {
        //given
        Long deleteId = 1L;

        //when
        boolean delete = noticeService.delete(deleteId);

        //then
        assertThat(delete).isTrue();
    }

    @Test
    void view() {
        //given

        //when

        //then
    }
}