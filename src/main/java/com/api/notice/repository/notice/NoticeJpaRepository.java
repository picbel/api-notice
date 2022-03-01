package com.api.notice.repository.notice;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NoticeJpaRepository extends JpaRepository<NoticeEntity, Long> {

    @EntityGraph(attributePaths = {"attachmentFileEntities"})
    Optional<NoticeEntity> findById(Long id);

    @Modifying(clearAutomatically = true)
    @Query("update NoticeEntity n set n.viewCount = n.viewCount + 1 where n.id = :noticeId")
    void viewUpdate(@Param("noticeId") Long noticeId);
}
