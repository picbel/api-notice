package com.api.dashboard.repository;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseTimestamp {

    @CreatedDate
    @Column(updatable = false ,name = "create_date_time")
    private LocalDateTime createDateTime;

    @LastModifiedDate
    @Column(name = "update_date_time")
    private LocalDateTime updateDateTime;
}