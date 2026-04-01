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

@WebServlet("/EditServlet2")
public class EditServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        Emp employee = new Emp();
        employee.setId(id);
        employee.setName(name);
        employee.setPassword(password);
        employee.setEmail(email);
        employee.setCountry(country);

        int status = EmpDao.update(employee);

        if (status > 0) {
            response.sendRedirect("ViewServlet?status=updated");
            return;
        }

        UiRenderer.printDocumentStart(out, "Falha na Atualizacao");
        out.println("<main class='page message-layout'>");
        out.println("<section class='message-card'>");
        out.println("<div class='status-icon status-error'>!</div>");
        out.println("<span class='eyebrow'>Atualizacao interrompida</span>");
        out.println("<h1>Os dados nao puderam ser atualizados.</h1>");
        out.println("<p>Houve um problema ao salvar as alteracoes do funcionario. Revise os dados e tente novamente.</p>");
        out.println("<div class='form-actions'><a href='EditServlet?id=" + id + "' class='btn btn-primary'>Voltar para a edicao</a><a href='ViewServlet' class='btn btn-secondary'>Ir para a listagem</a></div>");
        out.println("</section>");
        out.println("</main>");
        UiRenderer.printFooter(out);
        out.close();
    }
}
