package by.task.telephoneexchange.web;

import by.task.telephoneexchange.di.ServiceCreationException;
import by.task.telephoneexchange.di.ServiceCreator;
import by.task.telephoneexchange.domain.Role;
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
import java.util.List;

/**
 * UserListServlet.
 * Date: 12/25/2020
 *
 * @author Anastasiya Bezmen
 */
public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("session_user");
            if (user != null && (user.getRole() == Role.ADMINISTRATOR)) {
                try (ServiceCreator creator = new ServiceCreator()) {
                    IUserService userService = creator.getUserService();
                    List<User> users = userService.findAll();
                    req.setAttribute("users", users);
                    req.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);
                    return;
                } catch (ServiceCreationException | ServiceException e) {
                    throw new ServletException(e);
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login.html?message=" + URLEncoder.encode("Доступ запрещен",
                "UTF-8"));
    }
}
