package br.com.leilao.selenium.login;

import br.com.leilao.selenium.PageObject;
import br.com.leilao.selenium.leiloes.LeiloesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class LoginPage extends PageObject {

    public static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        super(null);
        this.browser.navigate().to(URL_LOGIN);
    }

    public void preencheFormularioDeLogin(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public LeiloesPage efetuaLogin() {
        browser.findElement(By.id("submit")).submit();
        return new LeiloesPage(browser);
    }

    public Boolean isPaginaDeLogin() {
        return isPaginaDeLogin(URL_LOGIN);
    }

    public Boolean isPaginaDeLogin(String source) {
        return source.equals(browser.getCurrentUrl());
    }

    public String getNomeUsuarioLogado() {
        try {
            return browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void navegaParaPaginaDeLances() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public Boolean contemTextoCurrentUrl(String texto) {
        return browser.getCurrentUrl().contains(texto);
    }

    public Boolean contemTextoPageSource(String texto) {
        return browser.getPageSource().contains(texto);
    }
}
