package br.com.leilao.selenium;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    public static final String URL_LOGIN = "http://localhost:8080/login";

    private WebDriver browser;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    }

    @BeforeEach
    public void beforeEach() {
        this.browser = new ChromeDriver();
        this.browser.navigate().to(URL_LOGIN);
    }

    @AfterEach
    public void afterEach() {
        this.browser.quit();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("frubas");
        browser.findElement(By.id("submit")).submit();

        Assertions.assertNotEquals(URL_LOGIN, browser.getCurrentUrl());
        Assertions.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        browser.findElement(By.id("username")).sendKeys("invalido");
        browser.findElement(By.id("password")).sendKeys("123456");
        browser.findElement(By.id("submit")).submit();

        Assertions.assertEquals(URL_LOGIN + "?error", browser.getCurrentUrl());
        Assertions.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
        Assertions.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
        Assertions.assertEquals(URL_LOGIN, browser.getCurrentUrl());
        Assertions.assertFalse(browser.getCurrentUrl().contains("Dados do Leilão"));
    }
}
