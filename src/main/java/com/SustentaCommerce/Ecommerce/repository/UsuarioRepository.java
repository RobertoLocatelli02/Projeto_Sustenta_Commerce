package com.SustentaCommerce.Ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SustentaCommerce.Ecommerce.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	List<Usuario> findAllByUsuarioContainingIgnoreCase (String usuario);
	List<Usuario> findAllByEmailUsuarioContainingIgnoreCase (String emailUsuario);
	Optional<Usuario> findByEmailUsuario(String emailUsuario);
}
