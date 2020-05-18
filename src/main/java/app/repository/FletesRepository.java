package app.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entities.Fletes;

@Repository
public interface FletesRepository extends JpaRepository<Fletes, BigDecimal> {

	@Query(name = "Fletes.findByOrigenDestino")
	public Fletes findCiudad(@Param("origen") String origen,@Param("destino") String destino);
}
