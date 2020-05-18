package app.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, BigDecimal> {

}
