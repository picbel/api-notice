package com.api.dashboard.model;

import com.api.dashboard.domain.AttachmentFile;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AttachmentFileDTO {
    private Long id;

    private String fileName;

    private String fileUrl;

    public AttachmentFileDTO(AttachmentFile attachmentFile) {
        this.id = attachmentFile.getId();
        this.fileName = attachmentFile.getFileName();
        this.fileUrl = attachmentFile.getFileUrl();
    }
}
