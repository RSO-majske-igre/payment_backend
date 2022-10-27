package team.marela.backend.database.entities.customer;

import lombok.*;
import lombok.experimental.SuperBuilder;
import team.marela.backend.database.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "address")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "unique_address", columnNames = {"line1", "line2", "city", "postalCode"}),
        @UniqueConstraint(name = "unique_city", columnNames = {"city", "postalCode"})
})
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AddressEntity extends BaseEntity {
    @Column(nullable = false)
    private String line1;

    private String line2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String postalCode;

    @OneToMany(mappedBy = "address")
    private Set<CustomerEntity> customers;
}
