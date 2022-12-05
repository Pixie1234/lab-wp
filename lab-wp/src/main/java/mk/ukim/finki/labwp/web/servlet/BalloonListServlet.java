package mk.ukim.finki.labwp.web.servlet;

import mk.ukim.finki.labwp.service.BalloonService;
import org.springframework.ui.Model;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

@WebServlet(name = "balloon-servlet", urlPatterns = "/")
public class BalloonListServlet extends HttpServlet {
    public BalloonListServlet(BalloonService balloonService,SpringTemplateEngine springTemplateEngine) {
        this.balloonService = balloonService;
        this.springTemplateEngine = springTemplateEngine;
    }

    private final BalloonService balloonService;
    private final SpringTemplateEngine springTemplateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context=new WebContext(req,resp,req.getServletContext());
        context.setVariable("balloons", balloonService.listAll());
        resp.setContentType("application/xhtml+xml");

        this.springTemplateEngine.process("listBalloons",context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String color=(String) req.getParameter("color");
        req.getSession().setAttribute("color",color);

    resp.sendRedirect("/selectBalloon");
    }
}
