package si.rso.majskeigre.payment_server.database.entities.customer;

import lombok.*;
import lombok.experimental.SuperBuilder;
import si.rso.majskeigre.payment_server.database.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "address")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "unique_address", columnNames = {"line1", "line2", "city", "postalCode"}),
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