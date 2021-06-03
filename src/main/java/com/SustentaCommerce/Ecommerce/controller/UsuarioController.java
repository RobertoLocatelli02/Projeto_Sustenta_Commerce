package com.SustentaCommerce.Ecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.SustentaCommerce.Ecommerce.model.UserLogin;
import com.SustentaCommerce.Ecommerce.model.Usuario;
import com.SustentaCommerce.Ecommerce.repository.UsuarioRepository;
import com.SustentaCommerce.Ecommerce.service.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repositoryU;
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/id/{id}")
	ResponseEntity<Usuario> findByIdUsuario(@PathVariable Long id) {
		return repositoryU.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	ResponseEntity<List<Usuario>> findAllByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repositoryU.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/usuario/{usuario}")
	ResponseEntity<List<Usuario>> findAllByUsuario(@PathVariable String usuario) {
		return ResponseEntity.ok(repositoryU.findAllByUsuarioContainingIgnoreCase(usuario));
	}

	@GetMapping("/email/{email}")
	ResponseEntity<List<Usuario>> findByEmail(@PathVariable String email) {
		return ResponseEntity.ok(repositoryU.findAllByEmailContainingIgnoreCase(email));
	}

	@GetMapping
	ResponseEntity<List<Usuario>> getAllUsuario() {
		List<Usuario> listaUsuarios = repositoryU.findAll();
		if (!listaUsuarios.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(listaUsuarios);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@PostMapping("/logar")
	public ResponseEntity<Optional<UserLogin>> logar(@RequestBody Optional<UserLogin> user) {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioService.logar(user));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@Valid @RequestBody Usuario usuarioNovo) {
		return usuarioService.cadastrarUsuario(usuarioNovo)
				.map(usuarioCadastrado -> ResponseEntity.status(201).body(usuarioCadastrado))
				.orElse(ResponseEntity.badRequest().build());

	}

	@PutMapping // atualizar informações de um produto
	ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuarioAtualizado) { // end point
		Optional<Usuario> usuarioExistente = repositoryU.findById(usuarioAtualizado.getId());
		if (usuarioExistente.isPresent()) {
			usuarioExistente.get().setUsuario(usuarioAtualizado.getUsuario());
			usuarioExistente.get().setEmail(usuarioAtualizado.getEmail());
			usuarioExistente.get().setSenha(usuarioAtualizado.getSenha());
			usuarioExistente.get().setNome(usuarioAtualizado.getNome());
			usuarioExistente.get().setUsuarioAdministrador(usuarioAtualizado.getUsuarioAdministrador());
			usuarioExistente.get().setUsuarioVendedor(usuarioAtualizado.getUsuarioVendedor());
			return ResponseEntity.status(HttpStatus.CREATED).body(repositoryU.save(usuarioExistente.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/id/{idUsuario}")
	void deleteUsuario(@PathVariable Long idUsuario) {
		repositoryU.deleteById(idUsuario);
	}
}