package com.sustentaCommerce.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProdutosService {
    
    @Autowired
    private ProdutosRepository repositoryP;
    @Autowired
    private UsuariosRepository repositoryU;
    @Autowired
    private CategoriasRepository repositoryC;

    public Optional<Produtos> postProduto(Produtos novoProduto, Long idCategoria, Long idUsuario) {
        novoProduto.setCategoria_produtos_criados(repositoryC.findById(idCategoria));
        novoProduto.setUsuario_produtos_criados(repositoryU.findById(idUsuario));
        return repositoryP.save(novoProduto);
}

    public Optional<Produtos> putProduto(Produtos produtoAtualizado, Long idCategoria, Long idUsuario) {
        produtoAtualizado.setCategoria_produtos_criados(repositoryC.finById(idCategoria));
        produtoAtualizado.setUsuario_produtos_criados(repositoryU.findById(idUsuario));
        return repositoryP.save(produtoAtualizado);
    }
