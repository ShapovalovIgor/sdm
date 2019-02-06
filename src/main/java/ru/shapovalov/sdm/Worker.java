package ru.shapovalov.sdm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shapovalov.sdm.repository.AttributesRepository;
import ru.shapovalov.sdm.repository.ObjectToAttributesRepository;
import ru.shapovalov.sdm.repository.ObjectsRepository;
import ru.shapovalov.sdm.repository.ParametersRepository;

@Service
public class Worker {

    @Autowired
    private AttributesRepository attributesRepository;

    @Autowired
    private ObjectsRepository objectsRepository;

    @Autowired
    private ObjectToAttributesRepository objectToAttributesRepository;

    @Autowired
    private ParametersRepository parametersRepository;
}
