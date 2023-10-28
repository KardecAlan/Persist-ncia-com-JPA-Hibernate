package br.com.kardec.loja.dao;

import br.com.kardec.loja.modelo.Categoria;
import br.com.kardec.loja.modelo.Produto;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Categoria categoria) {
        this.em.persist(categoria);
    }
}