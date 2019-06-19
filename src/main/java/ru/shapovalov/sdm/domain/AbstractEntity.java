package ru.shapovalov.sdm.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    private static final long serialVersionUID = -5023671718747011050L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "object_uuid")
    @Comment(text = "Уникальный идентификатор")
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
