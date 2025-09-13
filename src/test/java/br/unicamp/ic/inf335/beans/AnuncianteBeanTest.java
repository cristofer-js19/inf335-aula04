package br.unicamp.ic.inf335.beans;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AnuncianteBeanTest {

    @Test
    void deveCriarUmAnuncianteVazio() {
        AnuncianteBean anunciante = new AnuncianteBean();

        assertEquals("", anunciante.getNome());
        assertEquals("", anunciante.getCPF());
        assertTrue(anunciante.getAnuncios().isEmpty());
    }

    @Test
    void deveCriarUmAnuncianteComAtributosCustomizados() throws URISyntaxException, MalformedURLException {
        ArrayList<AnuncioBean> anuncios = new ArrayList<>();
        anuncios.add(new AnuncioBean
                (new ProdutoBean("CD00001","Celular Galaxy S10", "128 Gb, Preto, com Carregador",
                        1250.0,"Poucos riscos, bom estado de conservacao."), getUrls(), 10.0));

        AnuncianteBean anunciante =
                new AnuncianteBean("Joao da Silva", "01234567891", anuncios);

        assertEquals("Joao da Silva", anunciante.getNome());
        assertEquals("01234567891", anunciante.getCPF());
        assertFalse(anunciante.getAnuncios().isEmpty());
    }

    @Test
    void deveAtualizarAtributosDeUmAnunciante() throws MalformedURLException, URISyntaxException {
        AnuncianteBean anunciante = new AnuncianteBean();

        ArrayList<AnuncioBean> anuncios = new ArrayList<>();
        anuncios.add(new AnuncioBean
                (new ProdutoBean("CD00001","Celular Galaxy S10", "128 Gb, Preto, com Carregador",
                        1250.0,"Poucos riscos, bom estado de conservacao."), getUrls(), 10.0));

        anunciante.setNome("Joao da Silva");
        anunciante.setCPF("01234567891");
        anunciante.setAnuncios(anuncios);

        assertEquals("Joao da Silva", anunciante.getNome());
        assertEquals("01234567891", anunciante.getCPF());
        assertFalse(anunciante.getAnuncios().isEmpty());
    }

    @Test
    void deveAdicionarAnuncio() throws MalformedURLException, URISyntaxException {
        AnuncianteBean anunciante =
                new AnuncianteBean("Joao da Silva", "01234567891", new ArrayList<>());
        assertTrue(anunciante.getAnuncios().isEmpty());

        anunciante.addAnuncio(new AnuncioBean
                (new ProdutoBean("CD00002","iPhone 11", "256 Gb, Preto, sem Carregador",
                        4000.0,"Sem riscos, pouco usado."), getUrls(), 10.0));
        assertFalse(anunciante.getAnuncios().isEmpty());
    }

    @Test
    void deveRemoverAnuncio() throws MalformedURLException, URISyntaxException {
        ArrayList<AnuncioBean> anuncios = new ArrayList<>();
        anuncios.add(new AnuncioBean
                (new ProdutoBean("CD00001","Celular Galaxy S10", "128 Gb, Preto, com Carregador",
                        1250.0,"Poucos riscos, bom estado de conservacao."), getUrls(), 10.0));

        AnuncianteBean anunciante =
                new AnuncianteBean("Joao da Silva", "01234567891", anuncios);
        assertFalse(anunciante.getAnuncios().isEmpty());

        anunciante.removeAnuncio(0);
        assertTrue(anunciante.getAnuncios().isEmpty());
    }

    @Test
    void deveCalcularValorMedioQuandoExistemAnunciosInformados() throws MalformedURLException, URISyntaxException {
        ArrayList<AnuncioBean> anuncios = new ArrayList<>();
        anuncios.add(new AnuncioBean
                (new ProdutoBean("CD00001","Celular Galaxy S10", "128 Gb, Preto, com Carregador",
                        1250.0,"Poucos riscos, bom estado de conservacao."), getUrls(), 10.0));
        anuncios.add(new AnuncioBean
                (new ProdutoBean("CD00002","iPhone 11", "256 Gb, Preto, sem Carregador",
                        4000.0,"Sem riscos, pouco usado."), getUrls(), 10.0));
        AnuncianteBean anunciante =
                new AnuncianteBean("Joao da Silva", "01234567891", anuncios);

        assertEquals(2362.5, anunciante.valorMedioAnuncios());
    }

    @Test
    void deveRetornarValorMedioIgualAZeroQuandoNaoExistemAnunciosInformados() {
        AnuncianteBean anunciante =
                new AnuncianteBean("Joao da Silva", "01234567891", new ArrayList<>());
        assertEquals(0.0, anunciante.valorMedioAnuncios());
    }

    @Test
    void deveImpedirRemocaoDeAnunciosQuandoListaDeAnunciosEVazia() {
        AnuncianteBean anunciante =
                new AnuncianteBean("Joao da Silva", "01234567891", new ArrayList<>());
        assertDoesNotThrow(() -> anunciante.removeAnuncio(0));
    }

    private ArrayList<URL> getUrls() throws URISyntaxException, MalformedURLException {
        return new ArrayList<>() {{
            add(new URI("https://www.photos.com/photo/1").toURL());
        }};
    }
}
