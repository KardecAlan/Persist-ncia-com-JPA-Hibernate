package br.com.kardec.loja.testes;

import br.com.kardec.loja.dao.CategoriaDao;
import br.com.kardec.loja.dao.ClienteDao;
import br.com.kardec.loja.dao.PedidoDao;
import br.com.kardec.loja.dao.ProdudoDao;
import br.com.kardec.loja.modelo.*;
import br.com.kardec.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class PerformanceConsultas {
    public static void main(String[] args){
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedido = pedidoDao.buscarPedidoCOmCLiente(1l);
        em.close();
        System.out.println(pedido.getCliente().getNome());

    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videogames = new Categoria("VIDEOGAMES");
        Categoria informatica = new Categoria("INFORMATICA");

        Produto celular = new Produto("Xiami Redmi", "Barato e bom", new BigDecimal("800"), celulares);
        Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("3500"), videogames);
        Produto macbook = new Produto("Macbook", "Macbook para retirada", new BigDecimal("16000"), informatica);

        Cliente cliente = new Cliente("Kardec", "987654");

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, pedido,celular));
        pedido.adicionarItem(new ItemPedido(40, pedido, videogame));

        Pedido pedido2 = new Pedido(cliente);
        pedido2.adicionarItem(new ItemPedido(2, pedido, macbook));

        EntityManager em = JPAUtil.getEntityManager();
        ProdudoDao produtoDao = new ProdudoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);
        clienteDao.cadastrar(cliente);
        pedidoDao.cadastrar(pedido);

        em.getTransaction().commit();
        em.close();
    }
}
