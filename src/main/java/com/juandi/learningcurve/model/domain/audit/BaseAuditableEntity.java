package com.juandi.learningcurve.model.domain.audit;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EnableJpaAuditing
public class BaseAuditableEntity extends BaseEntity {
  @CreatedDate
  @Column(nullable = false, updatable = false)
  private Instant createdAt;

  @LastModifiedDate
  @Column(nullable = false)
  private Instant updatedAt;

  @CreatedBy
  @Column
  private String createdBy;

  @LastModifiedBy
  @Column
  private String updatedBy;

  @PrePersist
  protected void onCreate() {
    final Instant now = Instant.now();
    this.createdAt = now;
    this.updatedAt = now;
    if (this.createdBy == null) {
      this.createdBy = "system";
    }
    if (this.updatedBy == null) {
      this.updatedBy = "system";
    }
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = Instant.now();
    if (this.updatedBy == null) {
      this.updatedBy = "system";
    }
  }
}
