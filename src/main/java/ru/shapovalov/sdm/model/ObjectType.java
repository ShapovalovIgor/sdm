package ru.shapovalov.sdm.model;

import lombok.*;
import org.graalvm.compiler.lir.CompositeValue;
import org.jboss.logging.Field;
import org.w3c.dom.Comment;

import javax.persistence.*;
import java.util.UUID;

@RequiredArgsConstructor
@ToString(exclude="uuid")
@EqualsAndHashCode(exclude="uuid")
@Table(name = "object_type")
public class ObjectType {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "parent", nullable = true)
    private ObjectType parent;

    @Getter
    @Setter
    @NonNull
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "properties", nullable = true)
    private String properties;

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "version", nullable = false)
    private int version;

    @Getter
    @Setter
    @Column(name = "order_number", nullable = false)
    private int orderNumber;
}
