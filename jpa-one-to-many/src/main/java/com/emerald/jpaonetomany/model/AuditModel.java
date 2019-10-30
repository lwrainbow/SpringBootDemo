package com.emerald.jpaonetomany.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * The AuditModel class is an abstract class to make Post and Comment class can use these field
 */
@MappedSuperclass // Entity class can inherit this class
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
@Data // Lombok for getter/setter
public abstract class AuditModel implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonFormat(timezone = "America/Toronto", pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    @JsonFormat(timezone = "America/Toronto", pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private Date updatedAt;
}
