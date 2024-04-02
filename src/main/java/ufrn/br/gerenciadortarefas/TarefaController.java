package ufrn.br.gerenciadortarefas;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class TarefaController {

    @RequestMapping(value = "/doCadastroTarefa", method = RequestMethod.POST)
    public void doCadastroTarefa(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String texto = request.getParameter("texto");
        String prioridade = request.getParameter("prioridade");

        Tarefa tarefa = new Tarefa(texto, Integer.parseInt(prioridade));

        TarefaDao tarefaDao = new TarefaDao();

        tarefaDao.cadastrarTarefa(tarefa);

        //response.getWriter().println("<html><body><h1>Cadastro realizado com sucesso</h1></body></html>");

        response.sendRedirect("cadastroform.html");
    }

    @RequestMapping(value = "/gettarefa", method = RequestMethod.GET)
    public void doGetTarefa(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));

        TarefaDao tarefaDao = new TarefaDao();
        Tarefa tarefa = tarefaDao.getTarefaById(id);

        response.getWriter().println(tarefa.toString());
    }

    @RequestMapping("alltarefas")
    void doGetAllTarefas(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TarefaDao tarefaDao = new TarefaDao();
        List<Tarefa> tarefas = tarefaDao.listarTodasTarefas();

        var writer = response.getWriter();

        writer.println("<html> <head> <title> Todas as tareas </title> </head> <body> <table>");

        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        for (Tarefa t : tarefas){
            writer.println("<tr>");
            writer.println("<td>"+t.getTexto()+"</td>");
            writer.println("<td>"+t.getPrioridade()+"</td>");
            writer.println("<td>"+ formater.format(t.getData()) +"</td>");
            writer.println("</tr>");
        }
        writer.println("</table></body> </html>");
    }



}










