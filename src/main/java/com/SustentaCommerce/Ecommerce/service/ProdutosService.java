package com.SustentaCommerce.Ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SustentaCommerce.Ecommerce.model.Produtos;
import com.SustentaCommerce.Ecommerce.repository.CategoriaRepository;
import com.SustentaCommerce.Ecommerce.repository.ProdutosRepository;
import com.SustentaCommerce.Ecommerce.repository.UsuarioRepository;

@Service
public class ProdutosService {
    
    @Autowired
    private ProdutosRepository repositoryP;
    @Autowired
    private UsuarioRepository repositoryU;
    @Autowired
    private CategoriaRepository repositoryC;

    public Produtos postProduto(Produtos novoProduto, Long idCategoria, Long idUsuario) {
        novoProduto.setCategoria_produtos_criados(repositoryC.findById(idCategoria).get());
        novoProduto.setUsuario_produtos_criados(repositoryU.findById(idUsuario).get());
        return repositoryP.save(novoProduto);
}

    public Produtos putProduto(Produtos produtoAtualizado, Long idCategoria, Long idUsuario) {
        produtoAtualizado.setCategoria_produtos_criados(repositoryC.findById(idCategoria).get());
        produtoAtualizado.setUsuario_produtos_criados(repositoryU.findById(idUsuario).get());
        return repositoryP.save(produtoAtualizado);
    }
}
