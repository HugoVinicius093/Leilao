package br.com.leilao.selenium.login;

import br.com.leilao.selenium.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static br.com.leilao.selenium.login.LoginPage.URL_LOGIN;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    public void beforeEach() {
        this.loginPage = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.loginPage.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        loginPage.preencheFormularioDeLogin("fulano", "frubas");
        loginPage.efetuaLogin();

        Assertions.assertFalse(loginPage.isPaginaDeLogin());
        Assertions.assertEquals("fulano", loginPage.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        loginPage.preencheFormularioDeLogin("invalido", "123456");
        loginPage.efetuaLogin();

        Assertions.assertTrue(loginPage.isPaginaDeLogin(URL_LOGIN + "?error"));
        Assertions.assertNull(loginPage.getNomeUsuarioLogado());
        Assertions.assertTrue(loginPage.contemTextoPageSource("Usuário e senha inválidos."));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        loginPage.navegaParaPaginaDeLances();

        Assertions.assertTrue(loginPage.isPaginaDeLogin());
        Assertions.assertFalse(loginPage.contemTextoCurrentUrl("Dados do Leilão"));
    }
}
