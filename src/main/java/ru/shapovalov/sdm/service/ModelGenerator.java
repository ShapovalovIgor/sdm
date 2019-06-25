package ru.shapovalov.sdm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.shapovalov.sdm.domain.Attribute;
import ru.shapovalov.sdm.domain.Object;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.Collection;

@Transactional
@Component
public class ModelGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(ModelGenerator.class);

    private final AttributeService attributeService;

    private final ObjectService objectService;

    private final ObjectToAttributeService objectToAttributeService;

    private final ParameterService parameterService;

    public ModelGenerator(AttributeService attributeService,
                          ObjectService objectService,
                          ObjectToAttributeService objectToAttributeService,
                          ParameterService parameterService) {
        this.attributeService = attributeService;
        this.objectService = objectService;
        this.objectToAttributeService = objectToAttributeService;
        this.parameterService = parameterService;
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

            Object object = new Object();

            generateAttribute(cl);
        }
    }


    private void generateAttribute(Class<?> cl) {
        Field[] fields = cl.getDeclaredFields();

        for (Field field : fields) {
           Class<?> type = field.getType();
           type.getName();
        }
        LOG.info("length: " + fields.length);
    }

    private void fillTypeObject(Class<?> cl) {
       String type = cl.getSimpleName();
        Attribute attribute = new Attribute();
        attribute.setName(type);
//        attribute.setAttributeManagement();
    }
}
