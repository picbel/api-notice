package com.api.dashboard.repository;

import com.api.dashboard.domain.Notice;

public interface NoticeRepository {

    Notice save(Notice notice);

    Notice update(Notice notice);

    boolean delete(Long noticeId);
}
