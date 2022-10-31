package si.rso.majskeigre.payment_server.database;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseRepository<T> extends JpaRepository<T, UUID> {
}
