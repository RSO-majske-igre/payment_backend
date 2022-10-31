package si.rso.majskeigre.payment_server.database;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime created = LocalDateTime.now();
}
