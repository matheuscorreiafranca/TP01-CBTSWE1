/*
 * Disciplina: CBTSWE1 - ADS 571 
 * Professor: Wellington Tuler Moraes 
 * Trabalho Pratico 01 - CRUD 
 * Dupla: Matheus Correia de Franca e Davi Leite Coelho 
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        List<Emp> list = EmpDao.getAllEmployees();
        Set<String> countries = new LinkedHashSet<String>();
        for (Emp employee : list) {
            countries.add(employee.getCountry());
        }

        String status = request.getParameter("status");
        String toast = "";
        if ("updated".equals(status)) {
            toast = "Alteracao salva com sucesso.";
        } else if ("deleted".equals(status)) {
            toast = "Funcionario removido com sucesso.";
        }

        UiRenderer.printDocumentStart(out, "Lista de Funcionarios");
        out.println("<main class='page'>");
        out.println("<section class='hero'>");
        out.println("<article class='hero-card'>");
        out.println("<span class='eyebrow'>Painel de operacoes</span>");
        out.println("<h1>Equipe cadastrada, organizada e pronta para consulta.</h1>");
        out.println("<p class='hero-copy'>A listagem agora prioriza leitura, acao rapida e uma navegacao mais agradavel para acompanhar o CRUD inteiro.</p>");
        out.println("<div class='actions'>");
        out.println("<a href='index.html' class='btn btn-primary'>Cadastrar novo funcionario</a>");
        out.println("<a href='#tabela' class='btn btn-secondary'>Ir para a tabela</a>");
        out.println("</div>");
        if (!toast.isEmpty()) {
            out.println("<div class='toast'>" + UiRenderer.escape(toast) + "</div>");
        }
        out.println("</article>");
        out.println("<aside class='hero-aside'>");
        out.println("<div class='stat-card'><div class='stat-label'>Total de registros</div><div class='stat-value'>" + list.size() + "</div><p class='stat-note'>Visao consolidada dos funcionarios atualmente disponiveis no sistema.</p></div>");
        out.println("<div class='stat-card'><div class='stat-label'>Paises mapeados</div><div class='stat-value'>" + countries.size() + "</div><p class='stat-note'>Diversidade geografica registrada na base para consultas rapidas.</p></div>");
        out.println("</aside>");
        out.println("</section>");

        out.println("<section class='panel' id='tabela'>");
        out.println("<div class='panel-header'>");
        out.println("<div><span class='eyebrow'>Listagem completa</span><h2 class='panel-title'>Gestao de funcionarios</h2></div>");
        out.println("<p>Edite ou exclua registros com atalhos claros. Emails e paises ficam visiveis, enquanto a senha permanece protegida.</p>");
        out.println("</div>");

        if (list.isEmpty()) {
            out.println("<div class='table-wrap'><div class='empty-state'><h3>Nenhum funcionario cadastrado ainda.</h3><p>Comece pelo formulario inicial para preencher a base com os primeiros registros.</p><div class='secondary-actions'><a href='index.html' class='btn btn-primary'>Criar primeiro cadastro</a></div></div></div>");
        } else {
            out.println("<div class='table-wrap'>");
            out.println("<table>");
            out.println("<thead><tr><th>ID</th><th>Nome</th><th>Senha</th><th>Email</th><th>Pais</th><th>Acoes</th></tr></thead>");
            out.println("<tbody>");
            for (Emp employee : list) {
                String safeName = UiRenderer.escape(employee.getName());
                String confirmMessage = "Tem certeza que deseja excluir " + safeName + "?";
                out.println("<tr>");
                out.println("<td><span class='pill'>#" + employee.getId() + "</span></td>");
                out.println("<td>" + safeName + "</td>");
                out.println("<td>******</td>");
                out.println("<td>" + UiRenderer.escape(employee.getEmail()) + "</td>");
                out.println("<td>" + UiRenderer.escape(employee.getCountry()) + "</td>");
                out.println("<td><div class='secondary-actions'>"
                        + "<a href='EditServlet?id=" + employee.getId() + "' class='btn btn-secondary'>Editar</a>"
                        + "<a href='DeleteServlet?id=" + employee.getId() + "' class='btn btn-ghost' onclick='return confirm(\""
                        + confirmMessage
                        + "\")'>Excluir</a>"
                        + "</div></td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");
        }

        out.println("</section>");
        UiRenderer.printFooter(out);
        out.close();
    }
}
