package ru.shapovalov.sdm.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@ToString(exclude="uuid")
@EqualsAndHashCode(exclude="uuid")
@Table(name = "object_types")
public class Parameters {

    @Getter
    @Setter
    @Id
    @Column(name = "attribute_uuid")
    private UUID attribute_uuid;

    @Getter
    @Setter
    @NonNull
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "value")
    private String value;

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "version", nullable = false)
    private int version;

    @Getter
    @Setter
    @Column(name = "order_number", nullable = false)
    private int orderNumber;

    @Getter
    @Setter
    @OneToMany(mappedBy = "attribute_uuid", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Parameters> parametersList;
}
