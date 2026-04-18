import com.deque.html.axecore.results.AxeResults;
import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AcessibilidadeTest {

    @Test
    public void deveValidarAcessibilidadeLogin() throws Exception {

        WebDriver driver = new ChromeDriver();

        // abre arquivo local
        File file = new File("src/main/resources/login.html");

        driver.get(file.toURI().toString());

        Results results = new AxeBuilder().analyze(driver);

        // imprime no console
        results.getViolations().forEach(v -> {
            System.out.println("Impacto: " + v.getImpact());
            System.out.println("Descrição: " + v.getDescription());
            System.out.println("Ajuda: " + v.getHelpUrl());
            System.out.println("-------------------");
        });

        ObjectMapper mapper = new ObjectMapper();
        var somenteErros = results.getViolations();

        String json = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(somenteErros);

        String jsonBonito = mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(results);

        // salvar arquivo
        FileWriter writer = new FileWriter("relatorio-acessibilidade.json");
        writer.write(jsonBonito);
        writer.close();

        // imprimir no console
        System.out.println(jsonBonito);

        // validação
        assertFalse(results.getViolations().isEmpty(),
                "Existem problemas de acessibilidade!");

        driver.quit();
    }
}
