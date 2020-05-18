package app.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entities.CentroAcopio;

@Repository
public interface CentroAcopioRepository extends JpaRepository<CentroAcopio, BigDecimal> {

	@Query(name = "CentroAcopio.findByCiudad")
	public List<CentroAcopio> findCentroCiudad(@Param("ciudad") String ciudad);
}
