package com.api.dashboard.domain;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentFile {
    private Long id;

    private String fileName;

    private String fileUrl;

}
