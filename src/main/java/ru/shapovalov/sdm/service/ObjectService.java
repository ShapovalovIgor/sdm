package ru.shapovalov.sdm.service;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.shapovalov.sdm.domain.Object;
import ru.shapovalov.sdm.repository.ObjectRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ObjectService {

        @SuppressWarnings("unused")
        private static final Logger LOG = LoggerFactory.getLogger(ObjectService.class);

        private final ObjectRepository objectRepository;

        public ObjectService(ObjectRepository objectRepository) {
            this.objectRepository = objectRepository;
        }

        public Optional<Long> getCount() {
            Long count = this.objectRepository.count();

            return Optional.of(count);
        }

        public Optional<Object> getById(UUID id) {
            return this.objectRepository.findById(id);
        }


        public Optional<Object> save(@NotNull Object Object) {

            Object = this.objectRepository.save(Object);

            return Optional.of(Object);
        }

        
}
