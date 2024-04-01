package ufrn.br.gerenciadortarefas;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class TarefaController {

    @RequestMapping(value = "/doCadastroTarefa", method = RequestMethod.POST)
    public void doCadastroTarefa(HttpServletRequest request, HttpServletResponse response){

        String texto = request.getParameter("texto");
        String prioridade = request.getParameter("prioridade");

        Tarefa tarefa = new Tarefa();
        tarefa.setTexto(texto);
        tarefa.setPrioridade(Integer.parseInt(prioridade));

        TarefaDao tarefaDao = new TarefaDao();

        tarefaDao.cadastrarTarefa(tarefa);
    }

    @RequestMapping(value = "/gettarefa", method = RequestMethod.GET)
    public void doGetTarefa(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "id") Integer id) throws IOException {

        TarefaDao tarefaDao = new TarefaDao();

        Tarefa tarefa = tarefaDao.getTarefaById(id);

        response.getWriter().println(tarefa.toString());
    }
}
