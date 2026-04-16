package com.testes.Testeui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class FluxoTest extends BaseTest {

    @Test
    public void fluxoCompleto() {

        LoginPage login = new LoginPage(driver);
        UsuariosPage usuarios = new UsuariosPage(driver, wait);
        DetalhesPage detalhes = new DetalhesPage(driver, wait);
        PostPage post = new PostPage(driver, wait);

        // Login
        login.acessar();
        login.logar();

        // Carregar usuários
        usuarios.carregar();
        usuarios.aguardarTabela();

        assertTrue(usuarios.tabelaVisivel());
       
       //clicar no primeiro botão "Ver"
       driver.findElement(By.xpath("//button[text()='Ver']")).click();

       // Esperar carregar detalhes
       detalhes.aguardarNome();
        
       // Navegar para tela de post
       detalhes.clicarNovoPost();
        
       // Preencher e salvar
       post.preencherFormulario("João", "joao@email.com");
       post.salvar();

       // Espera explícita (mensagem dinâmica)
       post.aguardarMensagem();

       // Validação
       assertTrue(post.obterMensagem().contains("sucesso"));
    }
}
