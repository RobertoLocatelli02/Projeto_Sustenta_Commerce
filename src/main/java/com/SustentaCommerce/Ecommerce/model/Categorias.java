package com.SustentaCommerce.Ecommerce.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categoria")
public class Categorias {

	Categorias() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoria;
	
	@NotNull
	private String departamentoProduto;
	
	@OneToMany(mappedBy = "categoria_produtos_criados", fetch = FetchType.EAGER)
	@JsonIgnoreProperties("categoria_produtos")
	private List<Produtos> categoria_produtos = new ArrayList<>();

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDepartamentoProduto() {
		return departamentoProduto;
	}

	public void setDepartamentoProduto(String departamentoProduto) {
		this.departamentoProduto = departamentoProduto;
	}

	public List<Produtos> getCategoria_produtos() {
		return categoria_produtos;
	}

	public void setCategoria_produtos(List<Produtos> categoria_produtos) {
		this.categoria_produtos = categoria_produtos;
	}
	
}
