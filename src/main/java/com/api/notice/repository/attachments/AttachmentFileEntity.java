package com.api.notice.repository.attachments;

import com.api.notice.domain.AttachmentFile;
import com.api.notice.repository.BaseTimestamp;
import com.api.notice.repository.notice.NoticeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "attachment_file")
@Entity
@DynamicUpdate
public class AttachmentFileEntity extends BaseTimestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private NoticeEntity noticeEntity;

    public AttachmentFileEntity(AttachmentFile attachmentFile, NoticeEntity noticeEntity) {
        this.id = attachmentFile.getId();
        this.fileName = attachmentFile.getFileName();
        this.fileUrl = attachmentFile.getFileUrl();
        this.noticeEntity = noticeEntity;
    }

    public AttachmentFile toDomain(){
        return AttachmentFile.builder()
                .id(id)
                .fileUrl(fileUrl)
                .fileName(fileName)
                .build();
    }

}
