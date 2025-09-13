package br.unicamp.ic.inf335.beans;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AnuncioBeanTest {

    @Test
    void deveCriarUmAnuncioVazio() {
        AnuncioBean anuncio = new AnuncioBean();

        assertNotNull(anuncio.getProduto());
        assertTrue(anuncio.getFotosUrl().isEmpty());
        assertEquals(0.0, anuncio.getDesconto());
        assertEquals(0.0, anuncio.getValor());
    }

    @Test
    void deveCriarUmAnuncioComAtributosCustomizados() throws MalformedURLException, URISyntaxException {
        AnuncioBean anuncio =
                new AnuncioBean(new ProdutoBean("CD00001","Celular Galaxy S10", "128 Gb, Preto, com Carregador",
                        1250.0,"Poucos riscos, bom estado de conservacao."), getUrls("https://www.photos.com/photo/1"), 10.0);

        assertEquals("CD00001", anuncio.getProduto().getCodigo());
        assertFalse(anuncio.getFotosUrl().isEmpty());
        assertEquals(10.0, anuncio.getDesconto());
        assertEquals(1125.0, anuncio.getValor());
    }

    @Test
    void deveAtualizarAtributosDeUmAnuncio() throws URISyntaxException, MalformedURLException {
        AnuncioBean anuncio = new AnuncioBean();

        anuncio.setProduto(new ProdutoBean("CD00001","Celular Galaxy S10", "128 Gb, Preto, com Carregador",
                1250.0,"Poucos riscos, bom estado de conservacao."));
        anuncio.setFotosUrl(getUrls("https://www.photos.com/photo/1"));
        anuncio.setDesconto(10.0);

        assertEquals("CD00001", anuncio.getProduto().getCodigo());
        assertFalse(anuncio.getFotosUrl().isEmpty());
        assertEquals(10.0, anuncio.getDesconto());
        assertEquals(1125.0, anuncio.getValor());
    }

    @Test
    void deveGerarExcecaoParaURIsInvalidas() {
        assertThrows(URISyntaxException.class, () ->
                new AnuncioBean(new ProdutoBean("CD00001","Celular Galaxy S10", "128 Gb, Preto, com Carregador",
                        1250.0,"Poucos riscos, bom estado de conservacao."), getUrls("https://www.photos.com/photo/<1>"),10.0));
    }

    @Test
    void deveGerarExcecaoParaURLsInvalidas() {
        assertThrows(MalformedURLException.class, () ->
                new AnuncioBean(new ProdutoBean("CD00001","Celular Galaxy S10", "128 Gb, Preto, com Carregador",
                        1250.0,"Poucos riscos, bom estado de conservacao."), getUrls("dummyProtocol://www.photos.com/photo/1"),10.0));
    }

    private ArrayList<URL> getUrls(String url) throws URISyntaxException, MalformedURLException {
        return new ArrayList<>() {{
            add(new URI(url).toURL());
        }};
    }
}

