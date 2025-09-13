package br.unicamp.ic.inf335.beans;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoBeanTest {

    @Test
    void deveCriarUmProdutoVazio() {
        ProdutoBean p = new ProdutoBean();
        assertEquals("", p.getCodigo());
        assertEquals("", p.getNome());
        assertEquals("", p.getDescricao());
        assertEquals(0.0, p.getValor());
        assertEquals("", p.getEstado());
    }

    @Test
    void deveCriarUmProdutoComAtributosCustomizados() {
        ProdutoBean p = new ProdutoBean("CD00001","Celular Galaxy S10",
                "128 Gb, Preto, com Carregador",1250.0,"Poucos riscos, bom estado de conservacao.");

        assertEquals("CD00001", p.getCodigo());
        assertEquals("Celular Galaxy S10", p.getNome());
        assertEquals("128 Gb, Preto, com Carregador", p.getDescricao());
        assertEquals(1250.0, p.getValor());
        assertEquals("Poucos riscos, bom estado de conservacao.", p.getEstado());
    }

    @Test
    void deveAtualizarAtributosDeUmProduto() {
        ProdutoBean p = new ProdutoBean();
        p.setCodigo("CD00001");
        p.setNome("Celular Galaxy S10");
        p.setDescricao("128 Gb, Preto, com Carregador");
        p.setValor(1250.0);
        p.setEstado("Poucos riscos, bom estado de conservacao.");

        assertEquals("CD00001", p.getCodigo());
        assertEquals("Celular Galaxy S10", p.getNome());
        assertEquals("128 Gb, Preto, com Carregador", p.getDescricao());
        assertEquals(1250.0, p.getValor());
        assertEquals("Poucos riscos, bom estado de conservacao.", p.getEstado());
    }

    @Test
    void deveOrdenarUmaListaDeProdutos() {
        List<ProdutoBean> produtos = new ArrayList<>();
        produtos.add(new ProdutoBean("CD00001","Celular Galaxy S10", "128 Gb, Preto, com Carregador",1250.0,"Poucos riscos, bom estado de conservacao."));
        produtos.add(new ProdutoBean("CD00002","iPhone 11", "256 Gb, Preto, sem Carregador",4000.0,"Sem riscos, pouco usado."));
        produtos.add(new ProdutoBean("CD00003","Xaomi 7", "64 Gb, Branco, com Carregador",1000.0,"Muitos riscos, bastante desgastado."));
        produtos.add(new ProdutoBean("CD00004","Notebook Sony Vaio 10", "16 Gb, Prata, com Carregador",1500.0,"Razoável estado de conservação."));
        produtos.add(new ProdutoBean("CD00005","Notebook Acer 3", "4 Gb, Preto, com Carregador",750.0,"Bem antigo, porém conservado."));
        produtos.add(new ProdutoBean("CD00006","Notebook Hp Compaq", "8 Gb, Prata, com Carregador",500.0,"Bem antigo, estado médio de conservação."));

        Collections.sort(produtos);

        assertEquals("CD00002", produtos.get(0).getCodigo());
        assertEquals("CD00004", produtos.get(1).getCodigo());
        assertEquals("CD00001", produtos.get(2).getCodigo());
        assertEquals("CD00003", produtos.get(3).getCodigo());
        assertEquals("CD00005", produtos.get(4).getCodigo());
        assertEquals("CD00006", produtos.get(5).getCodigo());
    }
}

