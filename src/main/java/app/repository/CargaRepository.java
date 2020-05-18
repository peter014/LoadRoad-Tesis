package app.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Carga;

@Repository
public interface CargaRepository extends JpaRepository<Carga, BigDecimal> {

}
