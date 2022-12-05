package mk.ukim.finki.labwp.web.servlet;

import mk.ukim.finki.labwp.model.User;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="LoginServlet", urlPatterns = "/servlet/login")
public class LoginServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;

    public LoginServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }
    //private final AuthService authService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        springTemplateEngine.process("login.html",context,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        /*User user = null;
        try {
            user = authService.login(username,password);
        } catch (InvalidUserCredentialsException | InvalidArgumentsException ex ) {
            WebContext context = new WebContext(req,resp,req.getServletContext());
            context.setVariable("hasError",true);
            context.setVariable("error",ex.getMessage());
            springTemplateEngine.process("login.html",context,resp.getWriter());
            return;
        }
        req.getSession().setAttribute("user",user);
        resp.sendRedirect("/servlet/thymeleaf/category");*/

    }
}
