package mk.ukim.finki.labwp.web.servlet;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;

@WebServlet(name = "balloon-order", urlPatterns = "/BalloonOrder")
public class BalloonOrderServlet  extends HttpServlet {
    public BalloonOrderServlet(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    private final SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req,resp,req.getServletContext());
        this.springTemplateEngine.process("deliveryInfo.html",context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName=(String) req.getParameter("clientName");
        req.getSession().setAttribute("clientName",clientName);
        String clientAddress=(String)req.getParameter("clientAddress");
        req.getSession().setAttribute("clientAddress",clientAddress);
        resp.sendRedirect("/ConfirmationInfo");

    }
}
