package ru.shapovalov.sdm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.shapovalov.sdm.domain.Attribute;
import ru.shapovalov.sdm.domain.Object;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Component
public class ObjectWorker {
    private static final Logger LOG = LoggerFactory.getLogger(ObjectWorker.class);

    private final AttributeService attributeService;

    private final ObjectService objectService;

    private final ObjectToAttributeService objectToAttributeService;

    private final ParameterService parameterService;

    public ObjectWorker(AttributeService attributeService,
                        ObjectService objectService,
                        ObjectToAttributeService objectToAttributeService,
                        ParameterService parameterService) {
        this.attributeService = attributeService;
        this.objectService = objectService;
        this.objectToAttributeService = objectToAttributeService;
        this.parameterService = parameterService;
    }

    public void getObject(String name, String type) {

    }

    public void deleteObject(String name, String type) {

    }

    public void deleteAttribute(String name, String type) {

    }

    public void deleteAttributeParametr(String name, String type, String value) {

    }

    public void saveObject(Class<?> cl) {
        String objectType = "ObjectType";
        fillType(cl, objectType);
        List<Attribute> attributes = fillAttribute(cl);
        Object object = new Object();
        if (!attributes.isEmpty()) {
            object.setAttributes(attributes);
        }

        Class<?> superClass = cl.getSuperclass();
        if (superClass != null) {
            saveObject(superClass);
        }
    }

    private List<Attribute> fillAttribute(Class<?> cl) {
        String attributeType = "AttributeType";
        Field[] fields = cl.getDeclaredFields();
        String className = cl.getSimpleName();
        List<Attribute> attributes = new ArrayList<>();
        for (Field field : fields) {
            Class<?> type = field.getType();
            String name = field.getName();
            Attribute attributeManagement = fillType(type, attributeType);
            Optional<Attribute> optionalAttribute = attributeService.getByNameAndProertyLike(name, className + name);
            if (optionalAttribute.isPresent()) {
                Attribute attributeDB = optionalAttribute.get();
                Attribute attributeManagementDB = attributeDB.getAttributeManagement();
                if (attributeManagementDB != null) {
                    String attrManName = attributeManagementDB.getName();
                    String typeAttr = type.getName();
                    if ((attrManName != null && typeAttr != null && !attrManName.equals(typeAttr))
                            || (attrManName == null && typeAttr != null)
                            || (attrManName != null && typeAttr == null)) {
                        attributeDB.setAttributeManagement(attributeManagement);
                    }
                }
                attributes.add(attributeDB);
            }
        }
        return attributes;
    }

    private Attribute fillType(Class<?> cl, String property) {
        String type = cl.getSimpleName();
        Optional<Attribute> optionalAttribute = attributeService.getByNameAndProertyLike(type, property);
        if (optionalAttribute.isPresent()) {
            return optionalAttribute.get();
        } else {
            Attribute attribute = new Attribute();
            attribute.setName(type);
            return attributeService.save(attribute).get();
        }
    }
}
