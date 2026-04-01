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

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        Emp employee = new Emp();
        employee.setName(name);
        employee.setPassword(password);
        employee.setEmail(email);
        employee.setCountry(country);

        boolean duplicatedEmail = EmpDao.emailExists(email);
        int status = duplicatedEmail ? 0 : EmpDao.save(employee);

        UiRenderer.printDocumentStart(out, "Resultado do Cadastro");
        out.println("<main class='page message-layout'>");
        out.println("<section class='message-card'>");

        if (duplicatedEmail) {
            out.println("<div class='status-icon status-error'>!</div>");
            out.println("<span class='eyebrow'>Email ja utilizado</span>");
            out.println("<h1>Esse email ja existe no sistema.</h1>");
            out.println("<p>O endereco <strong>" + UiRenderer.escape(email) + "</strong> ja esta vinculado a outro funcionario. Revise o cadastro e tente novamente com um email diferente.</p>");
            out.println("<div class='form-actions'><a href='index.html' class='btn btn-primary'>Voltar ao formulario</a><a href='ViewServlet' class='btn btn-secondary'>Ver funcionarios</a></div>");
        } else if (status > 0) {
            out.println("<div class='status-icon status-success'>OK</div>");
            out.println("<span class='eyebrow'>Cadastro concluido</span>");
            out.println("<h1>Funcionario salvo com sucesso.</h1>");
            out.println("<p>O registro foi incluido na base e ja pode ser consultado ou editado na listagem principal.</p>");
            out.println("<div class='form-actions'><a href='ViewServlet' class='btn btn-primary'>Abrir listagem</a><a href='index.html' class='btn btn-secondary'>Cadastrar outro</a></div>");
        } else {
            out.println("<div class='status-icon status-error'>!</div>");
            out.println("<span class='eyebrow'>Falha no processo</span>");
            out.println("<h1>Nao foi possivel salvar agora.</h1>");
            out.println("<p>Houve um problema ao persistir os dados. Vale verificar a conexao com o banco e tentar novamente.</p>");
            out.println("<div class='form-actions'><a href='index.html' class='btn btn-primary'>Tentar novamente</a><a href='ViewServlet' class='btn btn-secondary'>Ir para a listagem</a></div>");
        }

        out.println("</section>");
        out.println("</main>");
        UiRenderer.printFooter(out);
        out.close();
    }
}
