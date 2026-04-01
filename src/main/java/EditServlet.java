/*
 * Disciplina: CBTSWE1 - ADS 571 
 * Professor: Wellington Tuler Moraes 
 * Trabalho Pratico 01 - CRUD 
 * Dupla: Matheus Correia de Franca e Davi Leite Coelho 
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);
        Emp employee = EmpDao.getEmployeeById(id);

        UiRenderer.printDocumentStart(out, "Atualizar Funcionario");
        out.println("<main class='page'>");
        out.println("<section class='hero'>");
        out.println("<article class='hero-card'>");
        out.println("<span class='eyebrow'>Edicao guiada</span>");
        out.println("<h1>Atualize os dados com o mesmo cuidado do cadastro inicial.</h1>");
        out.println("<p class='hero-copy'>A tela de edicao foi reorganizada para deixar a manutencao de registros mais clara, rapida e confortavel.</p>");
        out.println("<div class='actions'><a href='ViewServlet' class='btn btn-secondary'>Voltar para a listagem</a><a href='index.html' class='btn btn-primary'>Novo cadastro</a></div>");
        out.println("</article>");
        out.println("<aside class='hero-aside'>");
        out.println("<div class='stat-card'><div class='stat-label'>Registro atual</div><div class='stat-value'>#" + employee.getId() + "</div><p class='stat-note'>Edite apenas o necessario e salve para voltar direto para a listagem.</p></div>");
        out.println("<div class='stat-card'><div class='stat-label'>Email em foco</div><div class='stat-value'>Revisao</div><p class='stat-note'>Vale confirmar o email antes de salvar para manter a base consistente.</p></div>");
        out.println("</aside>");
        out.println("</section>");

        out.println("<section class='panel'>");
        out.println("<div class='panel-header'><div><span class='eyebrow'>Formulario de edicao</span><h2 class='panel-title'>Ajustar funcionario</h2></div><p>Altere os campos abaixo e conclua a atualizacao do cadastro.</p></div>");
        out.println("<form action='EditServlet2' method='post'>");
        out.println("<input type='hidden' name='id' value='" + employee.getId() + "'>");
        out.println("<div class='grid-2'>");
        out.println("<div class='field'><label for='name'>Nome completo</label><input type='text' id='name' name='name' value='" + UiRenderer.escape(employee.getName()) + "' required></div>");
        out.println("<div class='field'><label for='email'>Email</label><input type='email' id='email' name='email' value='" + UiRenderer.escape(employee.getEmail()) + "' required></div>");
        out.println("<div class='field'><label for='password'>Senha</label><input type='password' id='password' name='password' value='" + UiRenderer.escape(employee.getPassword()) + "' required></div>");
        out.println("<div class='field'><label for='country'>Pais</label><select id='country' name='country'>");
        printCountryOption(out, employee.getCountry(), "Brasil");
        printCountryOption(out, employee.getCountry(), "EUA");
        printCountryOption(out, employee.getCountry(), "Reino Unido");
        printCountryOption(out, employee.getCountry(), "Outro");
        out.println("</select></div>");
        out.println("<div class='field full'><span class='hint'>Apos salvar, voce retorna automaticamente para a listagem principal.</span></div>");
        out.println("</div>");
        out.println("<div class='form-actions'><button type='submit' class='btn btn-primary'>Salvar alteracoes</button><a href='ViewServlet' class='btn btn-secondary'>Cancelar</a></div>");
        out.println("</form>");
        out.println("</section>");
        UiRenderer.printFooter(out);
        out.close();
    }

    private void printCountryOption(PrintWriter out, String currentCountry, String option) {
        String selected = option.equals(currentCountry) ? " selected" : "";
        out.println("<option" + selected + ">" + UiRenderer.escape(option) + "</option>");
    }
}
