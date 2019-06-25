package ru.shapovalov.sdm.service;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.shapovalov.sdm.domain.Parameter;
import ru.shapovalov.sdm.repository.ParameterRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ParameterService {
    
        @SuppressWarnings("unused")
        private static final Logger LOG = LoggerFactory.getLogger(ParameterService.class);

        private final ParameterRepository parameterRepository;

        public ParameterService(ParameterRepository parameterRepository) {
            this.parameterRepository = parameterRepository;
        }

        public Optional<Long> getCount() {
            Long count = this.parameterRepository.count();

            return Optional.of(count);
        }

        public Optional<Parameter> getById(UUID id) {
            return this.parameterRepository.findById(id);
        }


        public Optional<Parameter> save(@NotNull Parameter Parameter) {

            Parameter = this.parameterRepository.save(Parameter);

            return Optional.of(Parameter);
        }

        
}
