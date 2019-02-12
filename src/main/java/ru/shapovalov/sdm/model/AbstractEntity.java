package ru.shapovalov.sdm.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = -5023671718747011050L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "object_uuid")
    @Comment(text = "Уникальный идентификатор")
    private UUID uuid;
}
