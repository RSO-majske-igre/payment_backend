package si.rso.majskeigre.payment_server.database;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"si.rso.majskeigre.payment_server.database.repositories"})
@EntityScan(basePackages = {"si.rso.majskeigre.payment_server.database.entities"})
public class ConfigurationDatabase {
}
