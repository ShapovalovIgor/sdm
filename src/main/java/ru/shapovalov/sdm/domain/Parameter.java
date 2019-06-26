package ru.shapovalov.sdm.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "parameter")
public class Parameter extends AbstractEntity{
    private static final long serialVersionUID = -8292643106420202229L;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="attribute", nullable = false, foreignKey = @ForeignKey(name = "parameter__attribute_fk"))
    private Attribute attribute;

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
}
