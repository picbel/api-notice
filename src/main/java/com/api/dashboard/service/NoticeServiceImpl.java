package com.api.dashboard.service;

import com.api.dashboard.domain.Notice;
import com.api.dashboard.model.NoticeDTO;
import com.api.dashboard.repository.notice.NoticeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;

    private final ObjectMapper objectMapper;

    @Override
    public Notice register(NoticeDTO noticeDTO) {
        noticeDTO.setViewCount(0);
        Notice notice = noticeDTO.toDomain(objectMapper);
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
        noticeRepository.viewNotice(noticeId);
        return noticeRepository.findById(noticeId);
    }
}
