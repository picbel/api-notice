package com.api.dashboard.repository.notice;

import com.api.dashboard.domain.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@RequiredArgsConstructor
class NoticeRepositoryImpl implements NoticeRepository{

    private final NoticeJpaRepository noticeJpaRepository;

    @Override
    public Notice save(Notice notice) {
        return noticeJpaRepository.save(new NoticeEntity(notice)).toDomain();
    }

    @Transactional
    @Override
    public Notice update(Notice notice) {
        return noticeJpaRepository.findById(notice.getId()).orElseThrow()
                .update(notice)
                .toDomain();
    }

    @Override
    public boolean deleteById(Long noticeId) {
        if (!noticeJpaRepository.existsById(noticeId)){
            return false;
        }else{
            noticeJpaRepository.deleteById(noticeId);
            return true;
        }
    }

    @Override
    public void viewNotice(Long noticeId) {
        noticeJpaRepository.viewUpdate(noticeId);
    }

    @Override
    public Notice findById(Long noticeId) {
        return noticeJpaRepository.findById(noticeId).orElseThrow().toDomain();
    }
}
