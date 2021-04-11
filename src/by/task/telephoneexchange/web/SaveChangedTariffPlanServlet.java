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

/**
 * SaveChangedTariffPlanServlet.
 * Date: 01/28/2021
 *
 * @author Anastasiya Bezmen
 */
public class SaveChangedTariffPlanServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountId = req.getParameter("accountId");
        String tariffPlanId = req.getParameter("tariffPlanId");
        try (ServiceCreator creator = new ServiceCreator()) {
            IUserService userService = creator.getUserService();
            userService.changeTariffPlan(accountId, tariffPlanId);
        } catch (ServiceCreationException | ServiceException | NumberFormatException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/user/personalAccount.html");
    }
}
