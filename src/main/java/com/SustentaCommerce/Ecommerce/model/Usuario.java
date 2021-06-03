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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario", uniqueConstraints=@UniqueConstraint(columnNames={"emailUsuario"}))
public class Usuario {
	
	Usuario() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	@Size(min = 5, max = 100)
	private String usuario;
	
	@NotNull
	@Size(min = 5, max = 100)
	private String email;
	
	@NotNull
<<<<<<< HEAD
	private String senha;
	
	private Boolean usuarioVendedor;
	
	private Boolean usuarioAdministrador;
=======
	private String senhaUsuario;
	private Boolean usuarioVendedor;
	private Boolean usuarioAdministrador;
	private String foto;
	
>>>>>>> 495de226134a3491522b4b55810ac58ef154b725
	
	@OneToMany(mappedBy = "usuario_produtos_criados", fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"usuario_produto"})
	private List<Produtos> usuario_produto = new ArrayList<>();

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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

<<<<<<< HEAD
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
=======
	public List<Produtos> getUsuario_produto() {
		return usuario_produto;
>>>>>>> 495de226134a3491522b4b55810ac58ef154b725
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getUsuarioVendedor() {
		return usuarioVendedor;
	}

	public void setUsuarioVendedor(Boolean usuarioVendedor) {
		this.usuarioVendedor = usuarioVendedor;
	}

	public Boolean getUsuarioAdministrador() {
		return usuarioAdministrador;
	}

	public void setUsuarioAdministrador(Boolean usuarioAdministrador) {
		this.usuarioAdministrador = usuarioAdministrador;
	}

	public List<Produtos> getUsuario_produto() {
		return usuario_produto;
	}

	public void setUsuario_produto(List<Produtos> usuario_produto) {
		this.usuario_produto = usuario_produto;
	}

	public Boolean getUsuarioVendedor() {
		return usuarioVendedor;
	}

	public void setUsuarioVendedor(Boolean usuarioVendedor) {
		this.usuarioVendedor = usuarioVendedor;
	}

	public Boolean getUsuarioAdministrador() {
		return usuarioAdministrador;
	}

	public void setUsuarioAdministrador(Boolean usuarioAdministrador) {
		this.usuarioAdministrador = usuarioAdministrador;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
