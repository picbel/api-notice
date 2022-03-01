package com.api.dashboard.service;

import com.api.dashboard.domain.Notice;
import com.api.dashboard.model.NoticeDTO;

public interface NoticeService {

    Notice register(NoticeDTO noticeDTO);

    Notice modify(NoticeDTO noticeDTO);

    boolean delete(Long noticeId);

    Notice view(Long noticeId);


}
