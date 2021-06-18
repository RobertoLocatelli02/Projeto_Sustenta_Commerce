package com.SustentaCommerce.Ecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
import com.SustentaCommerce.Ecommerce.model.Produtos;
import com.SustentaCommerce.Ecommerce.repository.ProdutosRepository;
import com.SustentaCommerce.Ecommerce.service.ProdutosService;

@RestController
@CrossOrigin("*")
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutosRepository repositoryP;
	@Autowired
	private ProdutosService produtoService;

	@GetMapping // retorna todos Produtos
	ResponseEntity<List<Produtos>> findAllProduto() { // end point
		return ResponseEntity.ok(repositoryP.findAll());
	}

	@GetMapping("/id/{id}") // retorna um produto por id
	ResponseEntity<Produtos> findByIdProduto(@PathVariable Long id) { // end point
		return repositoryP.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produtos>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repositoryP.findAllByNomeContainingIgnoreCase(nome));
	}

	@GetMapping("/precoUnitario/{precoUnitario}") // retorna todos os produtos por um preço unitario
	ResponseEntity<List<Produtos>> findByprecoUnitarioProdutos(@PathVariable Float precoUnitario) { // end point
		return ResponseEntity.ok(repositoryP.findAllByPrecoUnitario(precoUnitario));
	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Produtos>> getByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(repositoryP.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping // criar um novo produto
	ResponseEntity<Produtos> postProduto(@Valid @RequestBody Produtos produtoNovo) { // end point
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.postProduto(produtoNovo, produtoNovo.getCategoria_produtos_criados().getId(), produtoNovo.getUsuario_produtos_criados().getId()));
	}

	@PutMapping // atualizar informações de um produto
	ResponseEntity<Produtos> putProduto(@Valid @RequestBody Produtos produtoAtualizado) { // end point
		Optional<Produtos> produtoExistente = repositoryP.findById(produtoAtualizado.getId());
		if (produtoExistente.isPresent()) {
			produtoExistente.get().setNome(produtoAtualizado.getNome());
			produtoExistente.get().setDescricao(produtoAtualizado.getDescricao());
			produtoExistente.get().setPrecoUnitario(produtoAtualizado.getPrecoUnitario());
			produtoExistente.get().setQuantidade(produtoAtualizado.getQuantidade());
			produtoExistente.get().setFoto(produtoAtualizado.getFoto());
			produtoExistente.get().setCategoria_produtos_criados(produtoAtualizado.getCategoria_produtos_criados());
			produtoExistente.get().setUsuario_produtos_criados(produtoAtualizado.getUsuario_produtos_criados());			
			return ResponseEntity.status(HttpStatus.CREATED).body(repositoryP.save(produtoExistente.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/id/{id}") // deletar um produto criado
	void deleteProduto(@PathVariable Long id) { // end point
		repositoryP.deleteById(id);
	}

	@GetMapping("/pesquisar-produto")
	ResponseEntity<List<Produtos>> FullTextSearchProdutos(@Param("nome") String nome) {	
	
		List<Produtos> resultado = repositoryP.fullTextSearchProdutos(nome);
		return ResponseEntity.ok(resultado);
		
	}
}
