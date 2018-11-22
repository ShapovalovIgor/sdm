package ru.shapovalov.sdm.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@RequiredArgsConstructor
@ToString(exclude="uuid")
@EqualsAndHashCode(exclude="uuid")
@Table(name = "attributes")
public class Attributes {

    @Getter
    @Setter
    @ManyToOne
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "attribute_uuid")
    private UUID attribute_uuid;

    @Getter
    @Setter
    @NonNull
    @Column(name = "attribute_type")
    private String attributeType;

    @Getter
    @Setter
    @NonNull
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "properties")
    private String properties;
}
