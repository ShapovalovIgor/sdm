package ru.shapovalov.sdm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.shapovalov.sdm.domain.Objects;
import ru.shapovalov.sdm.repository.AttributesRepository;
import ru.shapovalov.sdm.repository.ObjectToAttributesRepository;
import ru.shapovalov.sdm.repository.ObjectsRepository;
import ru.shapovalov.sdm.repository.ParametersRepository;

import java.lang.reflect.Field;
import java.util.Collection;

@Service
public class ModelGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(ModelGenerator.class);

    private final AttributesRepository attributesRepository;

    private final ObjectsRepository objectsRepository;

    private final ObjectToAttributesRepository objectToAttributesRepository;

    private final ParametersRepository parametersRepository;

    public ModelGenerator(AttributesRepository attributesRepository, ObjectsRepository objectsRepository, ObjectToAttributesRepository objectToAttributesRepository, ParametersRepository parametersRepository) {
        this.attributesRepository = attributesRepository;
        this.objectsRepository = objectsRepository;
        this.objectToAttributesRepository = objectToAttributesRepository;
        this.parametersRepository = parametersRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void generateModel() {
        LOG.info("Start generate domain");
        generateObject();
    }

    private void generateObject() {
        Collection<Class<?>> cls = GeneratorUtils.getModelClasses();
        for (Class<?> cl : cls) {
            String objectType = cl.getSimpleName();
            LOG.info("ObjectType: " + objectType);

            Class<?> superClass = cl.getSuperclass();
            String parentType;
            if (superClass != null) {
                parentType = superClass.getName();
                LOG.info("ParentType: " + parentType);
            }

            Objects objects = new Objects();

            generateAttribute(cl);
        }
    }


    private StringBuilder generateAttribute(Class<?> cl) {
        StringBuilder fieldsSB = new StringBuilder();
        Field[] fields = cl.getDeclaredFields();

        for (Field field : fields) {
           Class<?> type = field.getType();
           type.getName();
        }
        LOG.info("length: " + fields.length);
        return fieldsSB;
    }
}
