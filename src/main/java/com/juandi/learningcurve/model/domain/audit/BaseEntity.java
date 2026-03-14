package com.juandi.learningcurve.model.domain.audit;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@MappedSuperclass
public class BaseEntity {

  @Version
  @SuppressWarnings("unused")
  private Long version;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
}
