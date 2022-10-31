package si.rso.majskeigre.payment_server.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import si.rso.majskeigre.payment_server.core.ConfigurationServices;
import si.rso.majskeigre.payment_server.database.ConfigurationDatabase;

@Configuration
@Import(value = {ConfigurationDatabase.class, ConfigurationServices.class})
public class ApplicationConfiguration {
}
