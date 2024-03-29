package ufrn.br.gerenciadortarefas;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TarefaController {

    @RequestMapping(value = "/doCadastroTarefa", method = RequestMethod.POST)
    public void doCadastroTarefa(HttpServletRequest request, HttpServletResponse response){

        String texto = request.getParameter("texto");
        String prioridade = request.getParameter("prioridade");

        Tarefa tarefa = new Tarefa();
        tarefa.setId(1);
        tarefa.setTexto(texto);
        tarefa.setPrioridade(Integer.parseInt(prioridade));

        TarefaDao tarefaDao = new TarefaDao();

        tarefaDao.cadastrarTarefa(tarefa);
        System.out.println("Cadastro passou");
    }
}
