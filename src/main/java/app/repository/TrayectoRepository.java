package app.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entities.Pedido;
import app.entities.Transportador;
import app.entities.Trayecto;

@Repository
public interface TrayectoRepository extends JpaRepository<Trayecto, BigDecimal> {

	@Query(name = "Trayecto.findById")
	public List<Trayecto> findCentroCiudad(@Param("id") String id);
	
	@Query(name = "Trayecto.findByPedido")
	public Trayecto findByPedido(@Param("pedidoFk") Pedido p);
	
	@Query(name = "Trayecto.findByTransportadorPedido")
	public Trayecto findByTransportadorPedido(@Param("transportadorFk") Transportador trans, @Param("pedidoFk") Pedido p);
}
