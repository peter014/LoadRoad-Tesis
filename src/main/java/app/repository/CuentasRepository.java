package app.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Cuentas;

@Repository
public interface CuentasRepository extends JpaRepository<Cuentas, BigDecimal> {

}
