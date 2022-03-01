package com.api.dashboard.repository.notice;

import com.api.dashboard.domain.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
class NoticeRepositoryImpl implements NoticeRepository{

    private final NoticeJpaRepository noticeJpaRepository;

    @Override
    public Notice save(Notice notice) {
        return noticeJpaRepository.save(new NoticeEntity(notice)).toDomain();
    }

    @Override
    public Notice update(Notice notice) {
        return null;
    }

    @Override
    public boolean deleteById(Long noticeId) {
        return false;
    }

    @Override
    public Integer viewNotice(Long noticeId) {
        return null;
    }

    @Override
    public Notice findById(Long noticeId) {
        return null;
    }
}
