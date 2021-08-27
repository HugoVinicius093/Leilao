package br.com.leilao.selenium.leiloes;

import br.com.leilao.selenium.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeiloesPage extends PageObject {

    public static final String URL_CADASTRO_LEILOES = "http://localhost:8080/leiloes/new";

    public static final String URL_LEILOES = "http://localhost:8080/leiloes";

    public LeiloesPage(WebDriver browser) {
       super(browser);
    }

    public CadastroLeilaoPage carregarFormulario() {
        this.browser.navigate().to(URL_CADASTRO_LEILOES);
        return new CadastroLeilaoPage(browser);
    }

    public boolean isLeilaoCadastrado(String nome, String valor, String data) {
        WebElement ultimaLinhaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = ultimaLinhaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaAbertura = ultimaLinhaTabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial = ultimaLinhaTabela.findElement(By.cssSelector("td:nth-child(3)"));
        return colunaNome.getText().equals(nome) && colunaAbertura.getText().equals(data) && colunaValorInicial.getText().equals(valor);
    }

    public boolean isPaginaAtual() {
        return browser.getCurrentUrl().contentEquals(URL_LEILOES);
    }
}