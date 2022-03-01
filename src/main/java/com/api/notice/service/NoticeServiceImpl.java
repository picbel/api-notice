package com.api.notice.service;

import com.api.notice.domain.Notice;
import com.api.notice.controller.model.NoticeDTO;
import com.api.notice.repository.notice.NoticeRepository;
import com.api.notice.util.exception.BusinessException;
import com.api.notice.util.exception.model.ErrorCode;
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
        Notice find = noticeRepository.findById(noticeId);
        if(find.isNotShow()){throw new BusinessException(ErrorCode.INQUIRY_PERIOD_EXPIRED);}
        noticeRepository.viewNotice(noticeId);
        return find;
    }
}
