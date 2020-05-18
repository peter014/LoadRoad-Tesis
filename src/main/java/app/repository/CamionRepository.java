package app.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Camion;

@Repository
public interface CamionRepository extends JpaRepository<Camion, BigDecimal> {

}
