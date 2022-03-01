package com.api.notice.service;

import com.api.notice.domain.Notice;
import com.api.notice.model.NoticeDTO;

public interface NoticeService {

    Notice register(NoticeDTO noticeDTO);

    Notice modify(NoticeDTO noticeDTO);

    boolean delete(Long noticeId);

    Notice view(Long noticeId);


}
