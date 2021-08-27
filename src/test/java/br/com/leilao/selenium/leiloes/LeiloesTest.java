package br.com.leilao.selenium.leiloes;

import br.com.leilao.selenium.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;

    private CadastroLeilaoPage paginaCadastroLeilao;

    @BeforeEach
    public void beforeEach() {
        LoginPage loginPage = new LoginPage();
        loginPage.preencheFormularioDeLogin("fulano", "frubas");
        this.paginaDeLeiloes = loginPage.efetuaLogin();
        this.paginaCadastroLeilao = paginaDeLeiloes.carregarFormulario();
    }

    @AfterEach
    public void afterEach() {
        this.paginaDeLeiloes.fechar();
    }

    @Test
    public void deveriaCadastrarLeilao(){
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia " + hoje;
        String valor = "500.00";

        this.paginaDeLeiloes = paginaCadastroLeilao.cadastrarLeilao(nome, valor, hoje);
        Assertions.assertTrue(paginaDeLeiloes.isLeilaoCadastrado(nome,valor, hoje));
    }

    @Test
    public void deveriaValidarCadastroDeLeilao(){
        this.paginaDeLeiloes = paginaCadastroLeilao.cadastrarLeilao("", "", "");

        Assertions.assertFalse(paginaCadastroLeilao.isPaginaAtual());
        Assertions.assertTrue(paginaDeLeiloes.isPaginaAtual());
        Assertions.assertTrue(paginaCadastroLeilao.isMensagemDeValicadaoVisiveis());

    }
}
