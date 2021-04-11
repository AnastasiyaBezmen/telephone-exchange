package by.task.telephoneexchange.web;

import by.task.telephoneexchange.di.ServiceCreationException;
import by.task.telephoneexchange.di.ServiceCreator;
import by.task.telephoneexchange.domain.User;
import by.task.telephoneexchange.service.IUserService;
import by.task.telephoneexchange.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * LoginServlet.
 * Date: 01/05/2021
 *
 * @author Anastasiya Bezmen
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && !login.isEmpty() && password != null) {
            try (ServiceCreator creator = new ServiceCreator()) {
                IUserService userService = creator.getUserService();
                User user = userService.authorize(login, password);
                if (user != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("session_user", user);
                    resp.sendRedirect(req.getContextPath() + "/index.html");
                    return;
                }
            } catch (ServiceCreationException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login.html?message=" + URLEncoder.encode("Логин или пароль введены" +
                " не корректно", "UTF-8"));
    }
}
