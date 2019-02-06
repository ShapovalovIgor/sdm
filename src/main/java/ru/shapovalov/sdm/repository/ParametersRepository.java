package ru.shapovalov.sdm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shapovalov.sdm.model.Parameters;

import java.util.UUID;

@Repository
public interface ParametersRepository extends CrudRepository<Parameters, UUID> {
}
