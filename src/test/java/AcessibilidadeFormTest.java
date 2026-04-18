import com.deque.html.axecore.results.AxeResults;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileWriter;

public class AcessibilidadeFormTest {
    @Test
    public void deveValidarSomenteFormulario() throws Exception {

        WebDriver driver = new ChromeDriver();

        File file = new File("src/main/resources/pagina.html");
        driver.get(file.toURI().toString());

        // 🔥 valida SOMENTE o formulário
        Results results = new AxeBuilder()
                .include("#formCadastro")
                .analyze(driver);

        // imprimir violações
        results.getViolations().forEach(v -> {
            System.out.println("Regra: " + v.getId());
            System.out.println("Impacto: " + v.getImpact());
            System.out.println("Descrição: " + v.getDescription());
            System.out.println("----------------------");
        });

        // gerar JSON legível
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(results);

        FileWriter writer = new FileWriter("relatorio-form.json");
        writer.write(json);
        writer.close();

        driver.quit();
    }
}

