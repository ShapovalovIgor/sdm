package ru.shapovalov.sdm.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AttributeOverride(name = "uuid", column = @Column(name = "uuid", updatable = false))
@Table(name = "parameter")
public class Parameter extends AbstractEntity{
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

    @OneToMany(mappedBy = "uuid",  fetch = FetchType.LAZY)
    private List<Parameter> parameterList;
}
