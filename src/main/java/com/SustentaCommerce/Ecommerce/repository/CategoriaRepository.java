package com.SustentaCommerce.Ecommerce.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SustentaCommerce.Ecommerce.model.Categorias;

@Repository
public interface CategoriaRepository extends JpaRepository<Categorias, Long>  {
	
public List<Categorias> findAllByDepartamentoProdutoContainingIgnoreCase (String departamentoProduto);
}
