package com.SustentaCommerce.Ecommerce.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.SustentaCommerce.Ecommerce.model.UserLogin;
import com.SustentaCommerce.Ecommerce.model.Usuario;
import com.SustentaCommerce.Ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario> cadastrarUsuario (Usuario usuarioNovo) {
		Optional<Usuario> usuarioExistente = repository.findByUsuario(usuarioNovo.getUsuario());
		if (usuarioExistente.isPresent()) {
			return Optional.empty();
		} else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuarioNovo.getSenha());
			usuarioNovo.setSenha(senhaCriptografada);
			return Optional.ofNullable(repository.save(usuarioNovo));
		}
	}
	
	public Optional<Usuario> atualizarUsuario(Usuario usuarioAtualizado) {
		Optional<Usuario> usuarioExistente = repository.findByUsuario(usuarioAtualizado.getUsuario());
		if(usuarioExistente.isPresent()) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuarioAtualizado.getSenha());
			usuarioAtualizado.setSenha(senhaCriptografada);
			return Optional.ofNullable(repository.save(usuarioAtualizado));
		} else {
			return Optional.empty();
		}
	}
	
	public Optional<UserLogin> logar(Optional<UserLogin> loginUser) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuarioPresente = repository.findByUsuario(loginUser.get().getUsuario());
		if(usuarioPresente.isPresent()) {
			if(encoder.matches(loginUser.get().getSenha(), usuarioPresente.get().getSenha())) {
				String auth = loginUser.get().getUsuario() + ":" + loginUser.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String token = "Basic " + new String(encodedAuth);
				
				loginUser.get().setToken(token);
				loginUser.get().setId(usuarioPresente.get().getId());
				loginUser.get().setNome(usuarioPresente.get().getNome());
				loginUser.get().setUsuario(usuarioPresente.get().getUsuario());
				loginUser.get().setSenha(usuarioPresente.get().getSenha());
				loginUser.get().setEmail(usuarioPresente.get().getEmail());
				loginUser.get().setUsuarioAdministrador(usuarioPresente.get().getUsuarioAdministrador());
				loginUser.get().setUsuarioVendedor(usuarioPresente.get().getUsuarioVendedor());
				return loginUser;
			}
		}
		return null;
	}

}
