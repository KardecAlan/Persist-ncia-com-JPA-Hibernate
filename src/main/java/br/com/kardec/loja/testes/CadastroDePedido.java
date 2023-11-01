package br.com.kardec.loja.testes;

import br.com.kardec.loja.dao.CategoriaDao;
import br.com.kardec.loja.dao.ClienteDao;
import br.com.kardec.loja.dao.PedidoDao;
import br.com.kardec.loja.dao.ProdudoDao;
import br.com.kardec.loja.modelo.*;
import br.com.kardec.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class CadastroDePedido {

    public static void main(String[] args){
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdudoDao produdoDao = new ProdudoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        Produto produto = produdoDao.buscarPorId(1l);
        Cliente cliente = clienteDao.buscarPorId(1l);

        em.getTransaction().begin();


        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10,pedido, produto));

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);

        em.getTransaction().commit();
        em.close();

    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiami Redmi", "Barato e bom", new BigDecimal("800"), celulares);

        Cliente cliente = new Cliente("Kardec", "987654");

        EntityManager em = JPAUtil.getEntityManager();
        ProdudoDao produtoDao = new ProdudoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);
        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }
}
