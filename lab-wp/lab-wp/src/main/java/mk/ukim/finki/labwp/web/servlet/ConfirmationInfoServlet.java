package mk.ukim.finki.labwp.web.servlet;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "confirmation-info",urlPatterns = "/ConfirmationInfo")
public class ConfirmationInfoServlet extends HttpServlet {
    public ConfirmationInfoServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    private final SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req,resp,req.getServletContext());
        context.setVariable("ipAddress",req.getRemoteAddr());
        context.setVariable("clientAgent",req.getHeader("User-Agent"));

        this.springTemplateEngine.process("confirmationInfo.html",context, resp.getWriter());
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String clientName=(String) req.getSession().getAttribute("clientName");
        //req.getSession().setAttribute("clientName",clientName);
       // req.getSession().invalidate();
        resp.sendRedirect("/balloons");
        //resp.sendRedirect("/orders");

    }
}
