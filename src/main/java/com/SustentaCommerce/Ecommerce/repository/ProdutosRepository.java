package com.SustentaCommerce.Ecommerce.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.SustentaCommerce.Ecommerce.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long>{

	List<Produtos> findAllByNomeContainingIgnoreCase (String nome);
	List<Produtos> findAllByDescricaoContainingIgnoreCase (String descricao);
	List<Produtos> findAllByPrecoUnitario (Float precoUnitario);
}
