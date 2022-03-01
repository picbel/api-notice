package com.api.dashboard.controller;

import com.api.dashboard.model.AttachmentFileDTO;
import com.api.dashboard.model.NoticeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:testData.sql"})
class NoticeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("등록 컨트롤러 테스트")
    @Test
    void create() throws Exception {
        List<AttachmentFileDTO> attachmentFiles = Arrays.asList(
                AttachmentFileDTO.builder().fileName("첨부파일 1").fileUrl("https://picbel.github.io/1").build(),
                AttachmentFileDTO.builder().fileName("첨부파일 2").fileUrl("https://picbel.github.io/2").build()
        );

        NoticeDTO noticeDTO = NoticeDTO.builder()
                .title("공지사항 제목 1")
                .content("공지사항 내용 1")
                .startDate(LocalDate.now().minusMonths(1))
                .endDate(LocalDate.now().plusDays(1))
                .attachmentFiles(attachmentFiles)
                .build();
        String body = objectMapper.writeValueAsString(noticeDTO);

        mockMvc.perform(
                post("/notice")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

    }

    @DisplayName("등록 컨트롤러 테스트 첨부파일 없음")
    @Test
    void create_2() throws Exception {

        NoticeDTO noticeDTO = NoticeDTO.builder()
                .title("공지사항 제목 2")
                .content("공지사항 내용 2")
                .startDate(LocalDate.now().minusMonths(1))
                .endDate(LocalDate.now().plusDays(1))
                .build();

        String body = objectMapper.writeValueAsString(noticeDTO);

        mockMvc.perform(
                post("/notice")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

    }

    @DisplayName("등록 컨트롤러 테스트 유효성검사 실패")
    @Test
    void create_3() throws Exception {

        NoticeDTO noticeDTO = NoticeDTO.builder()
                .title("공지사항 제목 1")
                .build();
        String body = objectMapper.writeValueAsString(noticeDTO);

        mockMvc.perform(
                post("/notice")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is4xxClientError())
                .andDo(print())
                .andReturn();

    }

    @DisplayName("수정 컨트롤러 테스트")
    @Test
    void update() throws Exception {
        List<AttachmentFileDTO> attachmentFiles = Arrays.asList(
                AttachmentFileDTO.builder().fileName("첨부파일 1").fileUrl("https://picbel.github.io/1").build(),
                AttachmentFileDTO.builder().fileName("첨부파일 2").fileUrl("https://picbel.github.io/2").build()
        );


        NoticeDTO noticeDTO = NoticeDTO.builder()
                .id(1L)
                .title("공지사항 제목 수정")
                .content("공지사항 내용 수정")
                .startDate(LocalDate.now().minusMonths(1))
                .endDate(LocalDate.now().plusDays(1))
                .attachmentFiles(attachmentFiles)
                .build();
        String body = objectMapper.writeValueAsString(noticeDTO);

        mockMvc.perform(
                put("/notice/1")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

    }

    @DisplayName("삭제 컨트롤러 테스트")
    @Test
    void deleteNotice() throws Exception {

        mockMvc.perform(
                delete("/notice/2")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

    }

    @DisplayName("조회 컨트롤러 테스트")
    @Test
    void view() throws Exception {

        mockMvc.perform(
                get("/notice/1")
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

    }




}