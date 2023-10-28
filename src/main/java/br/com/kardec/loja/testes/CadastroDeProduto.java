package br.com.kardec.loja.testes;

import br.com.kardec.loja.dao.CategoriaDao;
import br.com.kardec.loja.dao.ProdudoDao;
import br.com.kardec.loja.modelo.Categoria;
import br.com.kardec.loja.modelo.Produto;
import br.com.kardec.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDeProduto {

    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELULARES");


        Produto celular = new Produto("Xiami Redmi", "Barato e bom", new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdudoDao produtoDao = new ProdudoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }
}
