package com.SustentaCommerce.Ecommerce.model;

public class UserLogin {

	private Long id;
	private String nome;
	private String usuario;
	private String senha;
	private String token;
<<<<<<< HEAD
	private Boolean usuarioVendedor;
	private Boolean usuarioAdministrador;
=======
	private String foto;
	private Boolean usuarioVendedor;
	private Boolean usuarioAdministrador;
	private Long id;

>>>>>>> 495de226134a3491522b4b55810ac58ef154b725
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
<<<<<<< HEAD
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
}
=======

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
>>>>>>> 495de226134a3491522b4b55810ac58ef154b725

	public void setUsuarioAdministrador(Boolean usuarioAdministrador) {
		this.usuarioAdministrador = usuarioAdministrador;
	}

}
