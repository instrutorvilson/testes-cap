import com.deque.html.axecore.results.Results;

import java.io.FileWriter;

public class GeradorRelatorioHTML {
    public static void gerar(Results results) throws Exception {

        StringBuilder html = new StringBuilder();

        html.append("<html>");
        html.append("<head>");
        html.append("<meta charset='UTF-8'>");
        html.append("<title>Relatório de Acessibilidade</title>");

        // estilo básico
        html.append("<style>");
        html.append("body { font-family: Arial; margin: 20px; }");
        html.append("h1 { color: #333; }");
        html.append(".erro { border: 1px solid #ccc; padding: 10px; margin: 10px 0; }");
        html.append(".critical { background-color: #ffdddd; }");
        html.append(".serious { background-color: #ffe4b5; }");
        html.append(".moderate { background-color: #ffffcc; }");
        html.append("</style>");

        html.append("</head>");
        html.append("<body>");

        html.append("<h1>Relatório de Acessibilidade</h1>");
        html.append("<p>Total de violações: " + results.getViolations().size() + "</p>");

        for (var v : results.getViolations()) {

            html.append("<div class='erro " + v.getImpact() + "'>");

            html.append("<h3>" + v.getId() + "</h3>");
            html.append("<p><b>Impacto:</b> " + v.getImpact() + "</p>");
            html.append("<p><b>Descrição:</b> " + v.getDescription() + "</p>");
            html.append("<p><a href='" + v.getHelpUrl() + "' target='_blank'>Como corrigir</a></p>");

            html.append("<ul>");

            v.getNodes().forEach(node -> {
                html.append("<li>");
                html.append("<pre>" + node.getHtml().replace("<", "&lt;") + "</pre>");
                html.append("</li>");
            });

            html.append("</ul>");
            html.append("</div>");
        }

        html.append("</body></html>");

        FileWriter writer = new FileWriter("relatorio.html");
        writer.write(html.toString());
        writer.close();
    }
}
