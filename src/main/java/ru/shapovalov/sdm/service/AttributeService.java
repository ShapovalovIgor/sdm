package ru.shapovalov.sdm.service;

import com.sun.istack.internal.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.shapovalov.sdm.domain.Attribute;
import ru.shapovalov.sdm.repository.AttributeRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AttributeService {
    
        @SuppressWarnings("unused")
        private static final Logger LOG = LoggerFactory.getLogger(AttributeService.class);

        private final AttributeRepository attributeRepository;

        public AttributeService(AttributeRepository attributeRepository) {
            this.attributeRepository = attributeRepository;
        }

        public Optional<Long> getCount() {
            Long count = this.attributeRepository.count();

            return Optional.of(count);
        }

        public Optional<Attribute> getById(UUID id) {
            return this.attributeRepository.findById(id);
        }


        public Optional<Attribute> save(@NotNull Attribute Attribute) {

            Attribute = this.attributeRepository.save(Attribute);

            return Optional.of(Attribute);
        }

        
}
