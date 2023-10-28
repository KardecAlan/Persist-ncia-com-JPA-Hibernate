package br.com.kardec.loja.testes;

import br.com.kardec.loja.dao.ProdudoDao;
import br.com.kardec.loja.modelo.Produto;
import br.com.kardec.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class CadastroDeProduto {

    public static void main(String[] args) {
        Produto celular = new Produto();
        celular.setNome("Xiami Redmi");
        celular.setDescricao("Barato e bom");
        celular.setPreco(new BigDecimal("800"));

        EntityManager em = JPAUtil.getEntityManager();
        ProdudoDao dao = new ProdudoDao(em);

        em.getTransaction().begin();
        dao.cadastrar(celular);
        em.getTransaction().commit();
        em.close();
    }
}
