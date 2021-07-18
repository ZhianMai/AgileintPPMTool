package io.johnston.ppmtool.repositories;

import io.johnston.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Projectrepository extends CrudRepository<Project, Long> {
  @Override
  Iterable<Project> findAllById(Iterable<Long> iterable);
}
