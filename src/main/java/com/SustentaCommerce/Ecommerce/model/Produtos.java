package com.SustentaCommerce.Ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produto")
public class Produtos {
	
	Produtos() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String descricao;
	
	@NotNull
	private Float precoUnitario;

	@NotNull
	private String foto;
	
	@NotNull
	private Integer quantidade;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_produtos", referencedColumnName = "id")
	@JsonIgnoreProperties({"categoria_produtos","idCategoria"})
	private Categorias categoria_produtos_criados;

	@ManyToOne
	@JoinColumn(name = "usuario_produto", referencedColumnName = "id")
	private Usuario usuario_produtos_criados;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Float getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Float precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Categorias getCategoria_produtos_criados() {
		return categoria_produtos_criados;
	}

	public void setCategoria_produtos_criados(Categorias categoria_produtos_criados) {
		this.categoria_produtos_criados = categoria_produtos_criados;
	}

	public Usuario getUsuario_produtos_criados() {
		return usuario_produtos_criados;
	}

	public void setUsuario_produtos_criados(Usuario usuario_produtos_criados) {
		this.usuario_produtos_criados = usuario_produtos_criados;
	}

	
}