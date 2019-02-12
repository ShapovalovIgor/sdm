package ru.shapovalov.sdm.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@AttributeOverride(name = "uuid", column = @Column(name = "uuid", updatable = false))
@Table(name = "object_types")
public class Parameters extends AbstractEntity{

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
