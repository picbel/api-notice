package com.api.notice.model;

import com.api.notice.domain.AttachmentFile;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentFileDTO {
    private Long id;

    @NotNull(message = "파일 이름은 필수입니다.")
    private String fileName;

    @URL(message = "url 형식으로 입력하여 주십시오.")
    private String fileUrl;

    public AttachmentFileDTO(AttachmentFile attachmentFile) {
        this.id = attachmentFile.getId();
        this.fileName = attachmentFile.getFileName();
        this.fileUrl = attachmentFile.getFileUrl();
    }
}
