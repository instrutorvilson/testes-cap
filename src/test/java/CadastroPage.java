import com.microsoft.playwright.Page;

public class CadastroPage {
    private Page page;

    public CadastroPage(Page page){
        this.page = page;
    }

    public void preencherNome(String nome){
        page.fill("#nome", nome);
    }

    public void clicarBotao(){
        page.click("#btnSalvar");
    }

    public String obterMensagem(){
        return page.textContent("#mensagem");
    }
    public void acessarpagina(){
        page.navigate("http://127.0.0.1:5500/index.html");
    }
}
