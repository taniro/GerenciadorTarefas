package ufrn.br.gerenciadortarefas;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class AuthController {

    @RequestMapping(value = "logar", method = RequestMethod.POST)
    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var login = request.getParameter("login");
        var password = request.getParameter("password");

        if (login.equals("taniro") && password.equals("123")){
            HttpSession session = request.getSession();
            session.setAttribute("logado", true);

            response.sendRedirect("cadastroform.html");
        }else{
            response.sendRedirect("index.html?msg=Login falhou");
        }
    }

    @RequestMapping("/logout")
    public void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect("index.html?msg=Usuario deslogado");
    }
}
