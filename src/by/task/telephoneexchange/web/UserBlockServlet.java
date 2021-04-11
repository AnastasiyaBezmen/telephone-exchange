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
import java.io.IOException;
import java.util.List;

/**
 * ${NAME}.
 * Date: 01/24/2021
 *
 * @author Anastasiya Bezmen
 */
public class UserBlockServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServiceCreator creator = new ServiceCreator()) {
            IUserService userService = creator.getUserService();
            List<User> users = userService.findNegativeBalance();
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/jsp/user/block.jsp").forward(req, resp);
            return;
        } catch (ServiceCreationException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}

