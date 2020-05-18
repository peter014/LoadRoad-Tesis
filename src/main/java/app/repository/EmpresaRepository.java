package app.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, BigDecimal> {

}
