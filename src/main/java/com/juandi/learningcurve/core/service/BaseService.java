package com.juandi.learningcurve.core.service;

import com.juandi.learningcurve.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseService<T, IdT> {

  protected abstract JpaRepository<T, IdT> getRepository();

  protected abstract Class<?> getEntityClass();

  protected String entityName() {
    return getEntityClass().getSimpleName();
  }

  @Transactional(readOnly = true)
  public T findByIdOrThrow(final IdT id) {
    return getRepository()
        .findById(id)
        .orElseThrow(() -> new NotFoundException(entityName() + " " + id + " not found"));
  }

  @Transactional()
  public void deleteById(final IdT id) {
    final T entity = findByIdOrThrow(id);
    getRepository().delete(entity);
  }

  @Transactional(readOnly = true)
  public Page<T> findAll(final Pageable pageable) {
    return getRepository().findAll(pageable);
  }
}
