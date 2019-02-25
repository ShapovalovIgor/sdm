package ru.shapovalov.sdm.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@AttributeOverride(name = "uuid", column = @Column(name = "uuid", updatable = false))
@Table(name = "parameters")
public class Parameters extends AbstractEntity{
    private static final long serialVersionUID = -8292643106420202229L;

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

    @OneToMany(mappedBy = "parameters.uuid", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Parameters> parametersList;
}
