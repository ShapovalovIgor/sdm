package ru.shapovalov.sdm.model;

import lombok.*;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.UUID;

@RequiredArgsConstructor
@ToString(exclude="uuid")
@EqualsAndHashCode(exclude="uuid")
@Table(name = "object")
public class Object {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid")
    private UUID uuid;

    @Getter
    @Setter
    @Column(name = "parent")
    private Object parent;

    @Getter
    @Setter
    @NonNull
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @NonNull
    @Column(name = "object_type")
    private ObjectType objectType;

    @Getter
    @Setter
    @Column(name = "object_to_attribute")
    private ObjectToAttribute objectToAttribute;

    @Getter
    @Setter
    @Column(name = "properties")
    private String properties;

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_number")
    private int orderNumber;
}
