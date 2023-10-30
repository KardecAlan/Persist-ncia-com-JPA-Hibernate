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

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        em.persist(celulares);
        celulares.setNome("XPTO");

        em.flush();
        em.clear();

        celulares = em.merge(celulares);
        celulares.setNome("1234");
        em.flush();
        em.remove(celulares);
        em.flush();
    }
}
