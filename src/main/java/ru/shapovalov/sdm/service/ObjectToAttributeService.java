package ru.shapovalov.sdm.service;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.shapovalov.sdm.domain.ObjectToAttribute;
import ru.shapovalov.sdm.repository.ObjectToAttributeRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ObjectToAttributeService {

        @SuppressWarnings("unused")
        private static final Logger LOG = LoggerFactory.getLogger(ObjectToAttributeService.class);

        private final ObjectToAttributeRepository objectToAttributeRepository;

        public ObjectToAttributeService(ObjectToAttributeRepository objectToAttributeRepository) {
            this.objectToAttributeRepository = objectToAttributeRepository;
        }

        public Optional<Long> getCount() {
            Long count = this.objectToAttributeRepository.count();

            return Optional.of(count);
        }

        public Optional<ObjectToAttribute> getById(UUID id) {
            return this.objectToAttributeRepository.findById(id);
        }


        public Optional<ObjectToAttribute> save(@NotNull ObjectToAttribute ObjectToAttribute) {

            ObjectToAttribute = this.objectToAttributeRepository.save(ObjectToAttribute);

            return Optional.of(ObjectToAttribute);
        }

        
}
