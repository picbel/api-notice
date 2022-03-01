package com.api.dashboard.repository.notice;

import com.api.dashboard.domain.AttachmentFile;
import com.api.dashboard.domain.Notice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
//@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
class NoticeRepositoryTest {

    @Autowired
    NoticeRepository noticeRepository;

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
        assertThat(save.getId()).isEqualTo(1L);
    }

    @Test
    void update() {
        //given

        //when

        //then
    }

    @Test
    void deleteById() {
        //given

        //when

        //then
    }

    @Test
    void viewNotice() {
        //given

        //when

        //then
    }

    @Test
    void findById() {
        //given

        //when

        //then
    }
}