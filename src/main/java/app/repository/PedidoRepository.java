package app.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, BigDecimal> {

}
