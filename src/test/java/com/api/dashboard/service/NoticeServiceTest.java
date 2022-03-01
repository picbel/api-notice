package com.api.dashboard.service;

import com.api.dashboard.domain.Notice;
import com.api.dashboard.fixture.NoticeRepositoryResolver;
import com.api.dashboard.model.NoticeDTO;
import com.api.dashboard.repository.notice.NoticeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;

import static com.api.dashboard.fixture.ObjectMapperFixture.OBJECT_MAPPER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(NoticeRepositoryResolver.class)
class NoticeServiceTest {

    private NoticeService noticeService;

    @BeforeEach
    void setUp(NoticeRepository noticeRepository) {
        noticeService = new NoticeServiceImpl(noticeRepository, OBJECT_MAPPER);
    }

    @DisplayName("공지사항 등록")
    @Test
    void register() {
        //given
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .title("공지사항 제목 1")
                .content("공지사항 내용 1")
                .endDate(LocalDate.now().plusDays(1))
                .build();

        //when
        Notice register = noticeService.register(noticeDTO);

        //then
        assertThat(register.getId()).isNotNull();

    }

    @DisplayName("공지사항 등록 에러 / 종료날짜가 현재 날짜보다 이전")
    @Test
    void register_2() {
        //given
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .title("공지사항 제목 1")
                .content("공지사항 내용 1")
                .startDate(LocalDate.now().minusMonths(1))
                .endDate(LocalDate.now().minusDays(1))
                .build();

        //when //then
        assertThatThrownBy(() -> noticeService.register(noticeDTO))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("종료일을 현재 날짜보다 이후로 설정하여 주십시오.");

    }

    @DisplayName("공지사항 수정")
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

    @DisplayName("공지사항 삭제")
    @Test
    void delete() {
        //given
        Long deleteId = 1L;

        //when
        boolean delete = noticeService.delete(deleteId);

        //then
        assertThat(delete).isTrue();
    }

    @DisplayName("공지사항 조회")
    @Test
    void view() {
        //given
        Long viewId = 1L;

        //when
        Notice view = noticeService.view(viewId);

        //then
        assertThat(view)
                .extracting("id", "title", "content")
                .containsExactly(1L, "공지사항 제목 1", "공지사항 내용 1");

    }
}