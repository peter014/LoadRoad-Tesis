package app.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, BigDecimal> {

	@Query(name = "Usuario.findAll")
	public List<Usuario> findAll();
	
	@Query(name = "Usuario.findByUsuario")
	public Usuario findUsuario(@Param("usuario") String us);
	
	@Query(name = "Usuario.findByNombre")
	public Usuario findUsuarioByName(@Param("nombre") String nombre);
}
