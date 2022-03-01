package com.api.notice.repository.notice;

import com.api.notice.domain.AttachmentFile;
import com.api.notice.domain.Notice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
class NoticeRepositoryTest {

    @Autowired
    NoticeRepository noticeRepository;

    @DisplayName("등록 테스트")
    @Test
    void save() {
        //given
        List<AttachmentFile> attachmentFiles = Arrays.asList(
                AttachmentFile.builder().fileName("첨부파일 1").fileUrl("https://picbel.github.io/1").build(),
                AttachmentFile.builder().fileName("첨부파일 2").fileUrl("https://picbel.github.io/2").build()
        );

        Notice notice = Notice.builder()
                .title("공지사항 제목")
                .content("공지사항 내용")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(10))
                .viewCount(10)
                .writer("관리자1")
                .attachmentFiles(attachmentFiles)
                .build();
        //when
        Notice save = noticeRepository.save(notice);

        //then
        assertThat(save.getId()).isNotNull();
        assertThat(save.getAttachmentFiles().get(0).getId()).isNotNull();

    }

    @DisplayName("수정 테스트")
    @Test
    void update() {
        //given
        List<AttachmentFile> attachmentFiles = Arrays.asList(
                AttachmentFile.builder().id(1L).fileName("첨부파일 1").fileUrl("https://picbel.github.io/1").build(),
                AttachmentFile.builder().id(2L).fileName("첨부파일 2").fileUrl("https://picbel.github.io/2").build()
        );

        Notice notice = Notice.builder()
                .id(1L)
                .title("공지사항 제목")
                .content("공지사항 내용")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(10))
                .viewCount(10)
                .writer("관리자1")
                .attachmentFiles(attachmentFiles)
                .build();

        Notice save = noticeRepository.save(notice);

        Notice find = noticeRepository.findById(save.getId());

        Notice updateDomain = Notice.builder()
                .id(find.getId())
                .title("공지사항 제목 수정")
                .content("공지사항 내용 수정")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(30))
                .viewCount(10)
                .writer("관리자1")
                .attachmentFiles(find.getAttachmentFiles())
                .build();

        //when
        Notice update = noticeRepository.update(updateDomain);
        //then
        assertThat(update.getTitle()).isEqualTo("공지사항 제목 수정");

    }

    @DisplayName("삭제 테스트")
    @Test
    void deleteById() {
        //given
        Notice notice = Notice.builder()
                .title("삭제용")
                .content("공지사항 내용")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(10))
                .viewCount(10)
                .writer("관리자1")
                .build();

        Notice save = noticeRepository.save(notice);

        Long deleteId = save.getId();
        //when
        boolean b = noticeRepository.deleteById(deleteId);
        //then
        assertThat(b).isTrue();
    }

    @DisplayName("조회 서비스 테스트")
    @Test
    void viewNotice() {
        //given
        List<AttachmentFile> attachmentFiles = Arrays.asList(
                AttachmentFile.builder().fileName("첨부파일 1").fileUrl("https://picbel.github.io/1").build(),
                AttachmentFile.builder().fileName("첨부파일 2").fileUrl("https://picbel.github.io/2").build()
        );

        Notice notice = Notice.builder()
                .title("공지사항 제목")
                .content("공지사항 내용")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(10))
                .viewCount(10)
                .writer("관리자1")
                .attachmentFiles(attachmentFiles)
                .build();

        Notice save = noticeRepository.save(notice);

        //when
        noticeRepository.viewNotice(save.getId());

        //then
        Notice find = noticeRepository.findById(save.getId());
        assertThat(find)
                .extracting("title", "content","viewCount")
                .containsExactly("공지사항 제목", "공지사항 내용",11);
        assertThat(find.getAttachmentFiles().size()).isEqualTo(2);
    }

}