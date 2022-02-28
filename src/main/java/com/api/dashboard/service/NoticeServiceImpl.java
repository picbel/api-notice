package com.api.dashboard.service;

import com.api.dashboard.domain.Notice;
import com.api.dashboard.model.NoticeDTO;
import com.api.dashboard.repository.NoticeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;

    private final ObjectMapper objectMapper;

    @Override
    public Notice register(NoticeDTO noticeDTO) {
        return noticeRepository.save(noticeDTO.toDomain(objectMapper));
    }

    @Override
    public Notice modify(NoticeDTO noticeDTO) {
        return noticeRepository.update(noticeDTO.toDomain(objectMapper));
    }

    @Override
    public boolean delete(Long noticeId) {
        return noticeRepository.delete(noticeId);
    }

    @Override
    public Notice view(Long noticeId) {
        return null;
    }
}
