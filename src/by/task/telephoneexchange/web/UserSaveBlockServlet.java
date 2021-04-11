package by.task.telephoneexchange.web;

import by.task.telephoneexchange.di.ServiceCreationException;
import by.task.telephoneexchange.di.ServiceCreator;
import by.task.telephoneexchange.service.IUserService;
import by.task.telephoneexchange.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UserSaveBlockServlet.
 * Date: 01/25/2021
 *
 * @author Anastasiya Bezmen
 */
public class UserSaveBlockServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idsStr[] = req.getParameterValues("id");
        List<String> ids = new ArrayList<>(idsStr.length);
        Collections.addAll(ids, idsStr);
        try (ServiceCreator creator = new ServiceCreator()) {
            IUserService userService = creator.getUserService();
            userService.lockUser(ids);
        } catch (ServiceCreationException | ServiceException | NumberFormatException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/user/block.html");
    }
}
