package app.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Transportador;

public interface TransportadorRepository extends JpaRepository<Transportador, BigDecimal> {

}
