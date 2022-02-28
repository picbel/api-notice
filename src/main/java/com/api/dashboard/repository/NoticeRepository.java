package com.api.dashboard.repository;

import com.api.dashboard.domain.Notice;

public interface NoticeRepository {

    Notice save(Notice notice);

    Notice update(Notice notice);

    boolean deleteById(Long noticeId);

    Notice findById(Long noticeId);
}
