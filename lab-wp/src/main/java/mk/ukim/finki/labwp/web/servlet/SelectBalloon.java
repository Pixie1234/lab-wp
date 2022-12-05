package mk.ukim.finki.labwp.web.servlet;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "select-balloon", urlPatterns = "/selectBalloon")
public class SelectBalloon  extends HttpServlet {
    public SelectBalloon(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    private final SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req,resp,req.getServletContext());
        this.springTemplateEngine.process("selectBalloonSize.html",context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


           String size = (String) req.getParameter("size");
           req.getSession().setAttribute("size", size);
           resp.sendRedirect("/BalloonOrder");


    }
}
