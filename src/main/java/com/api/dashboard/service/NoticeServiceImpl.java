package com.api.dashboard.service;

import com.api.dashboard.domain.Notice;
import com.api.dashboard.model.NoticeDTO;
import com.api.dashboard.repository.notice.NoticeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;

    private final ObjectMapper objectMapper;

    @Override
    public Notice register(NoticeDTO noticeDTO) {
        Notice notice = noticeDTO.toDomain(objectMapper);
        if (notice.isEnd()){
            String format = String.format("종료일을 현재 날짜보다 이후로 설정하여 주십시오. 입력시간 : '%s' , 현재시간 : '%s'", notice.getEndDate(), LocalDate.now());
            throw new IllegalArgumentException(format);
        }
        return noticeRepository.save(notice);
    }

    @Override
    public Notice modify(NoticeDTO noticeDTO) {
        return noticeRepository.update(noticeDTO.toDomain(objectMapper));
    }

    @Override
    public boolean delete(Long noticeId) {
        return noticeRepository.deleteById(noticeId);
    }

    @Override
    public Notice view(Long noticeId) {
        return noticeRepository.findById(noticeId);
    }
}
