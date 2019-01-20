package ru.shapovalov.sdm.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Table(name = "object_types")
public class Parameters {

    @Id
    @Column(name = "attribute_uuid")
    private UUID attribute_uuid;

    @NonNull
    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "version", nullable = false)
    private int version;

    @Column(name = "order_number", nullable = false)
    private int orderNumber;

    @OneToMany(mappedBy = "attribute_uuid", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Parameters> parametersList;
}
