package ru.shapovalov.sdm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shapovalov.sdm.domain.Parameter;

import java.util.UUID;

@Repository
public interface ParameterRepository extends CrudRepository<Parameter, UUID> {
}
