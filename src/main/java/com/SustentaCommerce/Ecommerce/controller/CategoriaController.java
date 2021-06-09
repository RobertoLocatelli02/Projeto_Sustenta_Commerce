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
import com.SustentaCommerce.Ecommerce.model.Categorias;
import com.SustentaCommerce.Ecommerce.repository.CategoriaRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repositoryC;
	

	@GetMapping
	ResponseEntity<List<Categorias>> findAllCategoria() {
		return ResponseEntity.ok(repositoryC.findAll());
	}
	
	@GetMapping("/id/{id}")
	ResponseEntity<Categorias> findByIdCategoria(@PathVariable Long id) {
		return repositoryC.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/departamento/{departamentoProduto}")
	ResponseEntity<List<Categorias>> findByDepartamentoCategoria(@PathVariable String departamentoProduto) {
		return ResponseEntity.ok(repositoryC.findAllByDepartamentoProdutoContainingIgnoreCase(departamentoProduto));
	}
	
	@PostMapping
	ResponseEntity<Categorias> postCategoria(@Valid @RequestBody Categorias categoriaNovo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repositoryC.save(categoriaNovo));
	}
	
	@PutMapping
	ResponseEntity<Categorias> putCategoria(@Valid @RequestBody Categorias categoriaAtualizada) {
		
		Optional<Categorias> categoriaExistente = repositoryC.findById(categoriaAtualizada.getId());
		
		if (categoriaExistente.isPresent()) {
			categoriaExistente.get().setDepartamentoProduto(categoriaAtualizada.getDepartamentoProduto());
			return ResponseEntity.status(HttpStatus.CREATED).body(repositoryC.save(categoriaExistente.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/id/{id}")
	void deleteCategoria(@PathVariable Long id) {
		repositoryC.deleteById(id);
	}
}
