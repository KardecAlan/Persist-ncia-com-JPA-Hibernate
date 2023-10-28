package br.com.kardec.loja.dao;

import br.com.kardec.loja.modelo.Produto;

import javax.persistence.EntityManager;

public class ProdudoDao {

    private EntityManager em;

    public ProdudoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto){
        this.em.persist(produto);
    }
}
