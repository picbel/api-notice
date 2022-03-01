package com.api.notice.repository.notice;

import com.api.notice.domain.Notice;

public interface NoticeRepository {

    Notice save(Notice notice);

    Notice update(Notice notice);

    boolean deleteById(Long noticeId);

    void viewNotice(Long noticeId);

    Notice findById(Long noticeId);
}
